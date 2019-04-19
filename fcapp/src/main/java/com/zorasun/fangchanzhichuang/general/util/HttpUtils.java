package com.zorasun.fangchanzhichuang.general.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.general.dialog.ProgressDialog;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * HttpUtils 工具类
 *
 * @author chenzhifeng
 * @version v1.0
 * @e-mail 731739299@qq.com
 * @copyright 2010-2015
 * @create-time 2015年5月14日 下午2:19:56
 */
@SuppressWarnings("deprecation")
public class HttpUtils {


    private static final String TAG = "HttpUtils";
    public final static String error = "解析出错！"; // 解析json
    public static String COMPRESSION_CONTENT_TYPE = "application/octet-stream"; // 压缩
    // 备用
    private static List<RequestThread> requesList = new ArrayList<RequestThread>(); // 请求列表
    private static final int CONNECT_TIMEOUT = 20000; // 连接超时的时间
    private static HttpUtils mHttpRequest = null;
    private static Context mContext = null;
    private static boolean result = false;
    private static AsyncHttpClient client;


//    static {
//        // client.setMaxRetriesAndTimeout(0, CONNECT_TIMEOUT); //
//        // 设置最大重连次数和连接超时时间,如果不设置，默认为10s
//        client.setTimeout(CONNECT_TIMEOUT); // 设置超时时间
//    }

    private HttpUtils() {
        //    // 实例化对象
        // 设置超时时间
        client = new AsyncHttpClient();
    }

    /**
     * 程序启动时必先调
     *
     * @return
     */
    public static HttpUtils getInstance() {
        if (mHttpRequest == null) {
            mHttpRequest = new HttpUtils();
        }
        return mHttpRequest;
    }

    // private HttpUtils() {
    // TimerListnerImpl.getInstance().addObservers(mTimerListner);
    // }

    /**
     * 请求实体
     *
     * @author zyj
     */
    public class RequestThread {
        public String ApiCmd;
        public RequestParams params;
        public HttpCallback aHttpCallback;
        public int needShowLoading;
        public Context context;
    }

    @SuppressWarnings("unused")
    private static Handler aHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // for (int i = 0; i < requesList.size(); i++)
            // {
            // doConnect(requesList.get(i));
            // requesList.remove(0);
            // }
        }

    };

    // TimerListner mTimerListner = new TimerListner() {
    //
    // @Override
    // public void onClockListner() {
    // if (result) {
    // connectTimeout++;
    // }
    // if (connectTimeout == 5) {
    // connectTimeout = 0;
    // result = false;
    // postParamsCache = "";
    // }
    //
    // // if (requesListCache != null && requesListCache.size() > 0)
    // // {
    // // // aHandler.sendEmptyMessage(0);
    // // }
    //
    // }
    //
    // };

    /**
     * 采用AsyncHttpClient的post方式实现
     *
     * @param ApiCmd        请求连接
     * @param params        请求的参数
     * @param showLoading   是否显示loading(0:无loading， 1：有loading)
     *                      是否是同一类型的请求，只是请求的type不一样(true：是， false：否)
     * @param repeat        是否可以重复提交(true:可重复， false：不可重复)
     * @param aHttpCallback 请求回调
     */
    public static void postNoRepeat(Context aContext, final String ApiCmd, final RequestParams params,
                                    final int showLoading, final boolean repeat, final HttpCallback aHttpCallback) {
        if (mHttpRequest == null) {
            HttpUtils.getInstance();
        }
        if (aContext == null) {
            AppLog.redLog(TAG, "aContext is null");
        } else {
            mContext = aContext;
        }
        if (isNetworkConnected(aContext)) {
            String url = ApiConfig.BASE_URL + ApiCmd; // 请求的地址
            if (ApiCmd.startsWith("http"))
                url = ApiCmd;
            addConnectNoRepeat(aContext, url, params, showLoading, repeat, aHttpCallback);
        } else {
            AppLog.redLog(TAG, "network error");
            ProgressDialog.getInstance().dismissDialog();
            aHttpCallback.onNetworkError();
        }
    }

    public static void postNoRepeat2(Context aContext, final String ApiCmd, final RequestParams params,
                                     final int showLoading, final boolean type, final boolean repeat, final HttpCallback aHttpCallback) {
        if (mHttpRequest == null) {
            HttpUtils.getInstance();
        }
        if (aContext == null) {
            AppLog.redLog(TAG, "aContext is null");
        } else {
            mContext = aContext;
        }
        if (isNetworkConnected(aContext)) {
            String url = ApiConfig.BASE_URL + ApiCmd; // 请求的地址
            if (ApiCmd.startsWith("http"))
                url = ApiCmd;
            addConnectNoRepeat(aContext, url, params, showLoading, type, repeat, aHttpCallback);
        } else {
            AppLog.redLog(TAG, "network error");
            ProgressDialog.getInstance().dismissDialog();
            aHttpCallback.onNetworkError();
        }
    }

    public static void addConnectNoRepeat(Context acontext, final String ApiCmd, final RequestParams params,
                                          int needShowLoading, final boolean type, final boolean repeat, final HttpCallback aHttpCallback) {

        if (!repeat) // 用于防止重复提交的
        {
            result = true;

            if (requesList != null && requesList.size() > 0) {
                for (RequestThread requestThread : requesList) {
                    if (ApiCmd.equals(requestThread.ApiCmd)) {
                        return;
                    }
                }
            }
            // if (!type) // 同一类型的请求，只是传的type参数不一样
            // {
            // // 有时网络数据快，重复的请求已经完成，不在队列中，出现多次点击重复提交的问题
            // if (postParamsCache != null && !"".equals(postParamsCache))
            // {
            // if (postParamsCache.equals(ApiCmd))
            // {
            // return;
            // }
            // }
            // }
        }
        RequestThread request = mHttpRequest.new RequestThread();
        request.ApiCmd = ApiCmd;
        request.params = params;
        request.aHttpCallback = aHttpCallback;
        request.needShowLoading = needShowLoading;
        request.context = acontext;
        requesList.add(request);
        // requesListCache.add(request);
        doConnect(request);
    }

    /**
     * 将请求加入队列中
     *
     * @param ApiCmd          请求连接
     * @param params          请求参数
     * @param needShowLoading 是否需要显示loading框
     * @param params          是否是同一类型的请求，只是请求的type不一样(true：是， false：否)
     * @param params          是否可以重复提交(true:可重复， false：不可重复)
     * @param aHttpCallback   请求回调
     */
    public static void addConnectNoRepeat(Context acontext, final String ApiCmd, final RequestParams params,
                                          int needShowLoading, final boolean repeat, final HttpCallback aHttpCallback) {

        if (!repeat) // 用于防止重复提交的
        {
            if (requesList != null && requesList.size() > 0) {
                for (RequestThread requestThread : requesList) {
                    if (ApiCmd.equals(requestThread.ApiCmd)) {
                        return;
                    }
                }
            }
        }
        RequestThread request = mHttpRequest.new RequestThread();
        request.ApiCmd = ApiCmd;
        request.params = params;
        request.aHttpCallback = aHttpCallback;
        request.needShowLoading = needShowLoading;
        request.context = acontext;
        requesList.add(request);
        // requesListCache.add(request);
        doConnect(request);
    }

    /**
     * 处理请求
     *
     * @param request
     */
    private static void doConnect(final RequestThread request) {
        if (request.needShowLoading == 1) {
            ProgressDialog.getInstance().createLoadingDialog(mContext);
        }

        if (AccountConfig.isLogin()) {
            AppLog.redLog(TAG, "<-accountId->" + String.valueOf(AccountConfig.getAccountId()));
            client.addHeader("accountId", String.valueOf(AccountConfig.getAccountId()));
            client.addHeader("random", String.valueOf(AccountConfig.getAccountRandom()));
        } else {
            client.addHeader("account", "");
            client.addHeader("userId", String.valueOf(""));
        }
        AppLog.redLog(TAG, request.ApiCmd + "?" + request.params.toString());
        // 执行post方法
        client.post(request.ApiCmd, request.params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] content) {

                JSONObject responsejson;
                try {
                    responsejson = new JSONObject(bytes2String(content));
                    int code = HttpUtils.getJSONInt(responsejson, "code");
                    String msg = HttpUtils.getJSONString(responsejson, "msg");
                    if (code == 3) {
                        AccountConfig.saveLoginData(false, "", "", 0, "", "", 0, "", "", 0, "", 0, "");
                        // if (mContext != null) {
                        // final GeneralDialog dialog = new GeneralDialog();
                        // dialog.showDialog(mContext, msg);
                        // dialog.setOneButton("确认");
                        // dialog.sure(new OnClickListener() {
                        //
                        // @Override
                        // public void onClick(View v) {
                        // dialog.dismiss();
                        // Intent intent = new Intent(mContext,
                        // LoginActivity.class);
                        // mContext.startActivity(intent);
                        // }
                        // });
                        // }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                request.aHttpCallback.onSuccess(bytes2String(content));
                requesList.remove(request);
            }


            @Override
            public void onFinish() {
                super.onFinish();

                if (requesList.size() == 0) {
                    ProgressDialog.getInstance().dismissDialog();
                }
            }


            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                AppLog.redLog(TAG, "onFailure" + arg0);
                if (requesList.contains(request)) {
                    doConnect(request);
                    requesList.remove(request);
                } else {
                    ProgressDialog.getInstance().dismissDialog();
                    request.aHttpCallback.onNetworkError();
                }
            }
        });
    }


    private String getStringFromOutput(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        for (Map.Entry<String, String> entirty : map.entrySet()) {
            if (isFirst)
                isFirst = false;
            else
                sb.append("&");

            sb.append(URLEncoder.encode(entirty.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entirty.getValue(), "UTF-8"));
        }
        return sb.toString();
    }

    /**
     * 判断网络
     *
     * @param aContext
     * @return
     */
    public static boolean isNetworkConnected(Context aContext) {
        try {
            if (aContext != null) {
                ConnectivityManager cm = (ConnectivityManager) aContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();
                return ni != null && ni.isConnectedOrConnecting();
            } else {
                return false;
            }
        } catch (Exception e) {
            AppLog.redLog(TAG, e.toString());
            return false;
        }
    }

    /**
     * 字节转化
     *
     * @param b
     * @return
     */
    public final static String bytes2String(byte[] b) {
        if (b == null) {
            return null;
        }
        String ret = null;
        try {
            ret = new String(b, "UTF-8");
            ret = ret.trim();
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * 编码处理
     *
     * @param aString
     * @return
     */
    public static String URLEncoder(String aString) {
        String ret = null;
        if (aString != null) {
            ret = java.net.URLEncoder.encode(aString);
        }
        return ret;
    }

    /**
     * 判断是否需要解压缩
     *
     * @param aResponse
     * @return
     */
    public static boolean iSResponseNeedDecompress(HttpResponse aResponse) {
        boolean ret = false;
        if (aResponse != null) {
            Header head = aResponse.getEntity().getContentType();
            HeaderElement[] headvalue = head.getElements();
            if (headvalue != null && headvalue.length > 0) {
                String ContentType = headvalue[0].toString();
                if (ContentType.equals(COMPRESSION_CONTENT_TYPE)) {
                    ret = true;
                } else if (ContentType.equals("text/javascript")) {
                    ret = false;
                }
            }
        }
        return ret;
    }

    /**
     * 压缩
     *
     * @param data
     * @return
     */
    public static byte[] compress(byte[] data) {
        if (data == null) {
            return null;
        }
        byte[] output = null;
        Deflater compresser = new Deflater();
        compresser.reset();
        compresser.setInput(data);
        compresser.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
            bos.close();
        } catch (Exception e) {
            output = data;
        }

        compresser.end();
        return output;
    }

    /**
     * 解压缩
     *
     * @param data
     * @return
     */
    public static byte[] decompress(byte[] data) {
        byte[] output = new byte[0];
        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);
        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!decompresser.finished()) {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
            o.close();
        } catch (Exception e) {
            output = data;
            return null;
        }
        decompresser.end();
        return output;
    }

    /**
     * 解析josn
     *
     * @param aObject
     * @param name
     * @return
     */
    public final static String getJSONString(JSONObject aObject, String name) {
        String ret = "";
        if (aObject != null) {
            try {
                Object obj = aObject.isNull(name) ? null : aObject.get(name);
                if (obj != null && (obj instanceof String || obj instanceof Integer)) {
                    ret = String.valueOf(obj);
                }
            } catch (JSONException e) {
                AppLog.yellowLog(TAG, name + error);
            }
        }
        return ret;
    }

    /**
     * 解析josn
     *
     * @param aObject
     * @param name
     * @return
     */
    public final static JSONObject getJSONObject(JSONObject aObject, String name) {
        JSONObject ret = null;
        if (aObject != null) {
            try {
                Object obj = aObject.isNull(name) ? null : aObject.get(name);
                if (obj != null && obj instanceof JSONObject) {
                    ret = (JSONObject) obj;
                }
            } catch (JSONException e) {
                AppLog.yellowLog(TAG, name + error);
            }
        }
        return ret;
    }

    /**
     * 解析josn
     *
     * @param aObject
     * @param name
     * @return
     */
    public final static JSONArray getJSONArray(JSONObject aObject, String name) {
        JSONArray ret = null;
        if (aObject != null) {
            try {
                Object obj = aObject.isNull(name) ? null : aObject.get(name);
                if (obj != null && obj instanceof JSONArray) {
                    ret = (JSONArray) obj;
                }
            } catch (JSONException e) {
                AppLog.yellowLog(TAG, name + error);
            }
        }
        return ret;
    }

    /**
     * 解析josn
     *
     * @param aObject
     * @param name
     * @return
     */
    public static long getJSONLong(JSONObject aObject, String name) {
        long ret = 0;
        if (aObject != null) {
            try {
                ret = aObject.getLong(name);
            } catch (JSONException e) {
                AppLog.yellowLog(TAG, name + error);
            }
        }
        return ret;
    }

    /**
     * 解析josn
     *
     * @param aObject
     * @param name
     * @return
     */
    public static Double getJSONDouble(JSONObject aObject, String name) {
        Double ret = 0.0;
        if (aObject != null) {
            try {
                ret = aObject.getDouble(name);
            } catch (JSONException e) {
                AppLog.yellowLog(TAG, name + error);
            }
        }
        return ret;
    }

    /**
     * 解析josn
     *
     * @param aObject
     * @param name
     * @return
     */
    public static int getJSONInt(JSONObject aObject, String name) {
        int ret = 0;
        if (aObject != null) {
            try {
                ret = aObject.getInt(name);
            } catch (JSONException e) {
                AppLog.yellowLog(TAG, name + error);
            }
        }
        return ret;
    }

//    /**
//     * 自写联网
//     *
//     * @param httpurl
//     * @param aData
//     * @param aContentType
//     * @return
//     * @throws Exception
//     */
    public static byte[] GetDataFromUrlByPostData(String httpurl, byte[] aData, String aContentType) throws Exception {
        if (httpurl == null) {
            return null;
        }

        byte[] retstr = null;
        try {
            int timeoutConnection = 10000;
            int timeoutSocket = 200000;
            BasicHttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

            HttpClient httpclient = new DefaultHttpClient(httpParameters);

            HttpResponse response = null;
            HttpPost mCurrentHttpPost = new HttpPost(httpurl);
            mCurrentHttpPost.setEntity(new ByteArrayEntity(aData));
            if (aContentType != null) {
                mCurrentHttpPost.setHeader("Content-Type", aContentType);
                mCurrentHttpPost.setHeader("accountId", AccountConfig.getAccountId());

            }
            response = httpclient.execute(mCurrentHttpPost);
            AppLog.redLog(TAG, "<----=getStatusCode-------->" + response.getStatusLine().getStatusCode());
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                retstr = EntityUtils.toByteArray(response.getEntity());

                if (iSResponseNeedDecompress(response)) {
                    retstr = decompress(retstr);
                }
            }
        } catch (UnknownHostException e) {
            Log.e("printStackTrace", "printStackTrace" + " <-e---UnknownHostException----> " + e.toString());
            throw e;
        }
        return retstr;
    }

    /**
     * multipart方式传文件头
     *
     * @return
     */
    public static String GetPostContentType() {
        return "multipart/form-data; boundary=7dbfa4406b6";
    }

    /**
     * multipart方式传文件头
     *
     * @return
     */
    public static byte[] GetPostHead() {
        return "\r\n".getBytes();
    }

    /**
     * multipart方式 post传文件
     *
     * @return
     */
    public static byte[] GetPostContent(String aContentName, byte[] aContentData) {
        if (aContentData != null && aContentName != null) {
            byte[] head = ("\r\n--7dbfa4406b6\r\nContent-Disposition: form-data; name=\"" + aContentName + "\"\r\n\r\n")
                    .getBytes();
            byte[] retdata = new byte[head.length + aContentData.length];
            int offset = 0;
            System.arraycopy(head, 0, retdata, offset, head.length);
            offset += head.length;
            System.arraycopy(aContentData, 0, retdata, offset, aContentData.length);
            offset += aContentData.length;
            return retdata;
        } else {
            return null;
        }
    }

    /**
     * multipart方式 post传文件（添加了fileid）
     *
     * @return
     */
    public static byte[] GetPostFileContent(String aContentName, int fileId, String filename, String aContentType,
                                            byte[] aContentData) {
        if (aContentData != null && aContentName != null) {
            byte[] head = ("\r\n--7dbfa4406b6\r\nContent-Disposition: form-data; name=\"" + aContentName + "\";field=\""
                    + fileId + "\";filename=\"" + filename + "\"\r\nContent-Type: " + aContentType + "\r\n\r\n")
                    .getBytes();
            byte[] retdata = new byte[head.length + aContentData.length];
            int offset = 0;
            System.arraycopy(head, 0, retdata, offset, head.length);
            offset += head.length;
            System.arraycopy(aContentData, 0, retdata, offset, aContentData.length);
            offset += aContentData.length;
            return retdata;
        } else {
            return null;
        }
    }

    /**
     * multipart方式 post传文件
     *
     * @return
     */
    public static byte[] GetPostFileContent(String aContentName, String filename, String aContentType,
                                            byte[] aContentData) {
        if (aContentData != null && aContentName != null) {
            byte[] head = ("\r\n--7dbfa4406b6\r\nContent-Disposition: form-data; name=\"" + aContentName
                    + "\";filename=\"" + filename + "\"\r\nContent-Type: " + aContentType + "\r\n\r\n").getBytes();
            byte[] retdata = new byte[head.length + aContentData.length];
            int offset = 0;
            System.arraycopy(head, 0, retdata, offset, head.length);
            offset += head.length;
            System.arraycopy(aContentData, 0, retdata, offset, aContentData.length);
            offset += aContentData.length;
            return retdata;
        } else {
            return null;
        }

    }

    /**
     * multipart方式传文件尾巴
     *
     * @return
     */
    public static byte[] GetPostEnd() {
        return "\r\n--7dbfa4406b6--".getBytes();
    }

    public static String getBytesString(byte[] aData, int aOffset, int aDataLength) {
        byte[] retbytes = new byte[aDataLength];
        System.arraycopy(aData, aOffset, retbytes, 0, aDataLength);
        String ret = bytes2String(retbytes);
        retbytes = null;
        return ret;
    }
}
