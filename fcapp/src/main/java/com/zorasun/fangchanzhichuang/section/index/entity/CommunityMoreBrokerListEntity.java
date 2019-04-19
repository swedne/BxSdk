package com.zorasun.fangchanzhichuang.section.index.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;

public class CommunityMoreBrokerListEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2068536362710298720L;

    @Expose
    private Content content;

    /**
     * @return The content
     */
    public Content getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent(Content content) {
        this.content = content;
    }

    public class Content {

        @Expose
        private List<BrokerList> brokerList = new ArrayList<BrokerList>();

        /**
         * @return The brokerList
         */
        public List<BrokerList> getBrokerList() {
            return brokerList;
        }

        /**
         * @param brokerList The brokerList
         */
        public void setBrokerList(List<BrokerList> brokerList) {
            this.brokerList = brokerList;
        }

    }

    public class BrokerList {

        @Expose
        private BusinessService businessService;
        @Expose
        private Integer brokerId;
        @Expose
        private Integer IsExpert;
        @Expose
        private Object latitude;
        @Expose
        private String mobile;
        @Expose
        private String headUrl;
        @Expose
        private Integer businessListId;
        @Expose
        private String userName;
        @Expose
        private Object areaListName;
        @Expose
        private Integer uid;
        @Expose
        private String realName;
        @Expose
        private String harkBackHouse;
        @Expose
        private Double goodChance;
        @Expose
        private List<SpecialSkillList> specialSkillList = new ArrayList<SpecialSkillList>();

        public String getAllSkillList() {
            return allSkillList;
        }

        public void setAllSkillList(String allSkillList) {
            this.allSkillList = allSkillList;
        }

        @Expose
        private String allSkillList;
        @Expose
        private String brokerName;
        @Expose
        private String businessListName;
        @Expose
        private Object longitude;

        /**
         * @return The businessService
         */
        public BusinessService getBusinessService() {
            return businessService;
        }

        /**
         * @param businessService The businessService
         */
        public void setBusinessService(BusinessService businessService) {
            this.businessService = businessService;
        }

        /**
         * @return The brokerId
         */
        public Integer getBrokerId() {
            return brokerId;
        }

        /**
         * @param brokerId The brokerId
         */
        public void setBrokerId(Integer brokerId) {
            this.brokerId = brokerId;
        }

        /**
         * @return The IsExpert
         */
        public Integer getIsExpert() {
            return IsExpert;
        }

        /**
         * @param IsExpert The IsExpert
         */
        public void setIsExpert(Integer IsExpert) {
            this.IsExpert = IsExpert;
        }

        /**
         * @return The latitude
         */
        public Object getLatitude() {
            return latitude;
        }

        /**
         * @param latitude The latitude
         */
        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        /**
         * @return The mobile
         */
        public String getMobile() {
            return mobile;
        }

        /**
         * @param mobile The mobile
         */
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        /**
         * @return The headUrl
         */
        public String getHeadUrl() {
            return headUrl;
        }

        /**
         * @param headUrl The headUrl
         */
        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        /**
         * @return The businessListId
         */
        public Integer getBusinessListId() {
            return businessListId;
        }

        /**
         * @param businessListId The businessListId
         */
        public void setBusinessListId(Integer businessListId) {
            this.businessListId = businessListId;
        }

        /**
         * @return The userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         * @param userName The userName
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         * @return The areaListName
         */
        public Object getAreaListName() {
            return areaListName;
        }

        /**
         * @param areaListName The areaListName
         */
        public void setAreaListName(Object areaListName) {
            this.areaListName = areaListName;
        }

        /**
         * @return The uid
         */
        public Integer getUid() {
            return uid;
        }

        /**
         * @param uid The uid
         */
        public void setUid(Integer uid) {
            this.uid = uid;
        }

        /**
         * @return The realName
         */
        public String getRealName() {
            return realName;
        }

        /**
         * @param realName The realName
         */
        public void setRealName(String realName) {
            this.realName = realName;
        }

        /**
         * @return The harkBackHouse
         */
        public String getHarkBackHouse() {
            return harkBackHouse;
        }

        /**
         * @param harkBackHouse The harkBackHouse
         */
        public void setHarkBackHouse(String harkBackHouse) {
            this.harkBackHouse = harkBackHouse;
        }

        /**
         * @return The goodChance
         */
        public Double getGoodChance() {
            return goodChance;
        }

        /**
         * @param goodChance The goodChance
         */
        public void setGoodChance(Double goodChance) {
            this.goodChance = goodChance;
        }

        /**
         * @return The specialSkillList
         */
        public List<SpecialSkillList> getSpecialSkillList() {
            return specialSkillList;
        }

        /**
         * @param specialSkillList The specialSkillList
         */
        public void setSpecialSkillList(List<SpecialSkillList> specialSkillList) {
            this.specialSkillList = specialSkillList;
        }

        /**
         * @return The brokerName
         */
        public String getBrokerName() {
            return brokerName;
        }

        /**
         * @param brokerName The brokerName
         */
        public void setBrokerName(String brokerName) {
            this.brokerName = brokerName;
        }

        /**
         * @return The businessListName
         */
        public String getBusinessListName() {
            return businessListName;
        }

        /**
         * @param businessListName The businessListName
         */
        public void setBusinessListName(String businessListName) {
            this.businessListName = businessListName;
        }

        /**
         * @return The longitude
         */
        public Object getLongitude() {
            return longitude;
        }

        /**
         * @param longitude The longitude
         */
        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

    }

    public class BusinessService {

        @Expose
        private String businessName;
        @Expose
        private String hardBackHouse;

        /**
         * @return The businessName
         */
        public String getBusinessName() {
            return businessName;
        }

        /**
         * @param businessName The businessName
         */
        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        /**
         * @return The hardBackHouse
         */
        public String getHardBackHouse() {
            return hardBackHouse;
        }

        /**
         * @param hardBackHouse The hardBackHouse
         */
        public void setHardBackHouse(String hardBackHouse) {
            this.hardBackHouse = hardBackHouse;
        }

    }

    public class SpecialSkillList {

        @Expose
        private Integer brId;
        @Expose
        private String speciaName;
        @Expose
        private Integer speciaskillId;

        /**
         * @return The brId
         */
        public Integer getBrId() {
            return brId;
        }

        /**
         * @param brId The brId
         */
        public void setBrId(Integer brId) {
            this.brId = brId;
        }

        /**
         * @return The speciaName
         */
        public String getSpeciaName() {
            return speciaName;
        }

        /**
         * @param speciaName The speciaName
         */
        public void setSpeciaName(String speciaName) {
            this.speciaName = speciaName;
        }

        /**
         * @return The speciaskillId
         */
        public Integer getSpeciaskillId() {
            return speciaskillId;
        }

        /**
         * @param speciaskillId The speciaskillId
         */
        public void setSpeciaskillId(Integer speciaskillId) {
            this.speciaskillId = speciaskillId;
        }

    }

}
