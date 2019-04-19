package co.tiangongsky.bxsdkdemo.ui.start

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import co.bxvip.android.commonlib.utils.CommonInit.ctx
import co.bxvip.sdk.ui.BxStartActivityImpl
import co.tiangongsky.bxsdkdemo.ui.main.MainTestActivity
import com.qihoo360.replugin.RePlugin

class StartActivity : BxStartActivityImpl() {

    private var isJump = true;
    /* 是否检验 权限  默认是 false 校验*/
    override fun notCheckPermission(): Boolean {
        return super.notCheckPermission()
    }

    /* 没有网络跳进马甲  默认是 false  跳入 */
    override fun noNetworkJumpYourActivity(): Boolean {
        return super.noNetworkJumpYourActivity()
    }

    /* 是否显示 加载过程的文字  默认是 false 显示 */
    override fun hideLoadingShow(): Boolean {
        return super.hideLoadingShow()
    }

    /* 是否显示 底部版本信息  默认是 false 显示 */
    override fun hideVersionShow(): Boolean {
        return super.hideVersionShow()
    }

    /* 跳转你的马甲界面 */
    override fun toYourMainActivity() {
        // 方式 一启动 activity
//        startActivity(Intent(this, MainTestActivity::class.java))

//        // 方式二 启动插件 activity 界面
//        // 跳到主页 demo
//        val startActivity = RePlugin.startActivity(ctx,
//                RePlugin.createIntent("com.tiangong.android.plugin.demo",
//                        "com.tiangong.android.plugin.demo.MainActivity"))
//        if (startActivity) {
//            finish()
//        } else {
//            println("进入app失败！")
//        }

        // 方式三不使用sdk共有的库
        if (isJump) {
            isJump = false;
            //房产
//            val startActivity = RePlugin.startActivity(ctx,
//                    RePlugin.createIntent("com.zorasun.fangchanzhichuang",
//                            "com.zorasun.fangchanzhichuang.section.IntroActivity").setFlags(FLAG_ACTIVITY_NEW_TASK))
          //H5
            val startActivity = RePlugin.startActivity(ctx,
                    RePlugin.createIntent("com.swedne.application",
                            "com.swedne.application.MainActivity").setFlags(FLAG_ACTIVITY_NEW_TASK))
        }

//        val startActivity = RePlugin.startActivity(ctx,
//                RePlugin.createIntent("com.tiangong.plugin.nosdklib",
//                        "com.tiangong.plugin.nosdklib.MainActivity"))
//        if (startActivity) {
//            finish()
//        } else {
//            println("进入app失败！")
//        }
//
        finish()
    }
}
