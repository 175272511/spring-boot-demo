package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;
import java.util.Date;

import static org.springframework.data.elasticsearch.annotations.FieldType.String;

/**
 * Created by liuhui on 2016/12/8.
 */
@Document(indexName = "testik", type = "testiktype", shards = 1, replicas = 0, refreshInterval = "-1")
public class StoreFansDetail  {

    /**
     *  主健
     */
    @Id
    private Integer id;
    /**
     *  唯一识别码
     */
    @JsonProperty("SYS_UUID")
    private String sysUuid;
    /**
     *  商家UUID
     */
    @JsonProperty("MCH_UUID")
    private String mchUuid;
    /**
     *  OPEID
     */
    @JsonProperty("OPENID")
    private String openid;
    /**
     *  粉丝头像
     */
    @JsonProperty("FANS_HEAD")
    private String fansHead;
    /**
     *  粉丝昵称
     */
    @JsonProperty("FANS_NAME")
    private String fansName;
    /**
     *  上级门店
     */
    private String storeHigher;
    /**
     *  上级门店UUID
     */
    private String higherUuid;
    /**
     *  门店UUID
     */
    @JsonProperty("STORE_UUID")
    private String storeUuid;
    /**
     *  门店名称
     */
    @JsonProperty("STORE_NAME")
    private String storeName;
    /**
     *  店员UUID
     */
    @JsonProperty("STAFF_UUID")
    private String staffUuid;
    /**
     *  店员姓名
     */
    @JsonProperty("STAFF_NAME")
    private String staffName;
    /**
     *  手机号
     */
    private String staffPhone;
    /**
     *  所在省ID
     */
    private Integer provinceId;
    /**
     *  所在省
     */
    private String storeProvince;
    /**
     *  所在市ID
     */
    private Integer cityId;
    /**
     *  所在市
     */
    @Field(type = String,analyzer = "ik_pinyin_analyzer")
    private String storeCity;
    /**
     *  粉丝获取方式(分为”粉丝主动选择“ 1 、”手动分配粉丝“ 2 、”店员主动拉粉“ 3三种情况)
     */
    private String fansMode;
    /**
     *  来源渠道(分为”PC站聊天页面“ 1 、”M站聊天页面“ 2、”微信主动关注“ 3)
     */
    private String channel;
    /**
     *  更新时间
     */
    private Date attDate;
    /**
     *  是否关注(0否，1是)
     */
    private String isAtt;
    /**
     *  最近激活时间
     */
    private Date actDate;
    /**
     *  粉丝电话
     */
    private String fansPhone;

    /**
     *  取消关注时间
     */
    private Date cancalDate;
    /**
     *  创建时间
     */
    private Date createDate;

    /**
     *  性别gender
     */
    private   String  gender;

    /**
     * 离线消息推送时间记录
     * @return
     */
    private Date  pushDate;
    /**
     * 乐观版本号
     */
    private long version;
    //自定义字段 存储粉丝主动选择1，系统分配粉丝2，店员主动拉粉3  粉丝总和 数量
    private String fansModeZ;
    private String fansModeF;
    private String fansModeL;
    private String fansSum;

    private String guiRemark;//导购的备注名
    private String test;//导购的备注名
    private String test1;//导购的备注名
    private String test2;//导购的备注名
    private String test3;//导购的备注名

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getTest3() {
        return test3;
    }

    public void setTest3(String test3) {
        this.test3 = test3;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getFansSum() {
        return fansSum;
    }

    public void setFansSum(String fansSum) {
        this.fansSum = fansSum;
    }

    public String getFansModeZ() {
        return fansModeZ;
    }

    public void setFansModeZ(String fansModeZ) {
        this.fansModeZ = fansModeZ;
    }

    public String getFansModeF() {
        return fansModeF;
    }

    public void setFansModeF(String fansModeF) {
        this.fansModeF = fansModeF;
    }

    public String getFansModeL() {
        return fansModeL;
    }

    public void setFansModeL(String fansModeL) {
        this.fansModeL = fansModeL;
    }
    /**
     * 主健
     * @param id
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * 主健
     * @return
     */
    public Integer getId(){
        return id;
    }
    /**
     * 唯一识别码
     * @param sysUuid
     */
    public void setSysUuid(String sysUuid){
        this.sysUuid = sysUuid;
    }

    /**
     * 唯一识别码
     * @return
     */
    public String getSysUuid(){
        return sysUuid;
    }
    /**
     * 商家UUID
     * @param mchUuid
     */
    public void setMchUuid(String mchUuid){
        this.mchUuid = mchUuid;
    }

    /**
     * 商家UUID
     * @return
     */
    public String getMchUuid(){
        return mchUuid;
    }
    /**
     * OPEID
     * @param openid
     */
    public void setOpenid(String openid){
        this.openid = openid;
    }

    /**
     * OPEID
     * @return
     */
    public String getOpenid(){
        return openid;
    }
    /**
     * 粉丝头像
     * @param fansHead
     */
    public void setFansHead(String fansHead){
        this.fansHead = fansHead;
    }

    /**
     * 粉丝头像
     * @return
     */
    public String getFansHead(){
        return fansHead;
    }
    /**
     * 粉丝昵称
     * @param fansName
     */
    public void setFansName(String fansName){
        this.fansName = fansName;
    }

    /**
     * 粉丝昵称
     * @return
     */
    public String getFansName(){
        return fansName;
    }
    /**
     * 上级门店
     * @param storeHigher
     */
    public void setStoreHigher(String storeHigher){
        this.storeHigher = storeHigher;
    }

    /**
     * 上级门店
     * @return
     */
    public String getStoreHigher(){
        return storeHigher;
    }
    /**
     * 上级门店UUID
     * @param higherUuid
     */
    public void setHigherUuid(String higherUuid){
        this.higherUuid = higherUuid;
    }

    /**
     * 上级门店UUID
     * @return
     */
    public String getHigherUuid(){
        return higherUuid;
    }
    /**
     * 门店UUID
     * @param storeUuid
     */
    public void setStoreUuid(String storeUuid){
        this.storeUuid = storeUuid;
    }

    /**
     * 门店UUID
     * @return
     */
    public String getStoreUuid(){
        return storeUuid;
    }

    /**
     * 店员UUID
     * @param staffUuid
     */
    public void setStaffUuid(String staffUuid){
        this.staffUuid = staffUuid;
    }

    /**
     * 店员UUID
     * @return
     */
    public String getStaffUuid(){
        return staffUuid;
    }

    /**
     * 手机号
     * @param staffPhone
     */
    public void setStaffPhone(String staffPhone){
        this.staffPhone = staffPhone;
    }

    /**
     * 手机号
     * @return
     */
    public String getStaffPhone(){
        return staffPhone;
    }
    /**
     * 所在省ID
     * @param provinceId
     */
    public void setProvinceId(Integer provinceId){
        this.provinceId = provinceId;
    }

    /**
     * 所在省ID
     * @return
     */
    public Integer getProvinceId(){
        return provinceId;
    }
    /**
     * 所在省
     * @param storeProvince
     */
    public void setStoreProvince(String storeProvince){
        this.storeProvince = storeProvince;
    }

    /**
     * 所在省
     * @return
     */
    public String getStoreProvince(){
        return storeProvince;
    }
    /**
     * 所在市ID
     * @param cityId
     */
    public void setCityId(Integer cityId){
        this.cityId = cityId;
    }

    /**
     * 所在市ID
     * @return
     */
    public Integer getCityId(){
        return cityId;
    }
    /**
     * 所在市
     * @param storeCity
     */
    public void setStoreCity(String storeCity){
        this.storeCity = storeCity;
    }

    /**
     * 所在市
     * @return
     */
    public String getStoreCity(){
        return storeCity;
    }
    /**
     * 粉丝获取方式(分为”粉丝主动选择“、”手动分配粉丝“、”店员主动拉粉“三种情况)
     * @param fansMode
     */
    public void setFansMode(String fansMode){
        this.fansMode = fansMode;
    }

    /**
     * 粉丝获取方式(分为”粉丝主动选择“、”店长分配粉丝“、”店员主动拉粉“,"客服分配" 四种情况)
     * @return
     */
    public String getFansMode(){
        return fansMode;
    }
    /**
     * 来源渠道(分为”PC站聊天页面“、”M站聊天页面“、”微信主动关注“)
     * @param channel
     */
    public void setChannel(String channel){
        this.channel = channel;
    }

    /**
     * 来源渠道(分为”PC站聊天页面“、”M站聊天页面“、”微信主动关注“)
     * @return
     */
    public String getChannel(){
        return channel;
    }
    /**
     * 关注时间
     * @param attDate
     */
    public void setAttDate(Date attDate){
        this.attDate = attDate;
    }

    /**
     * 关注时间
     * @return
     */
    public Date getAttDate(){
        return attDate;
    }
    /**
     * 是否关注(0否，1是)
     * @param isAtt
     */
    public void setIsAtt(String isAtt){
        this.isAtt = isAtt;
    }

    /**
     * 是否关注(0否，1是)
     * @return
     */
    public String getIsAtt(){
        return isAtt;
    }
    /**
     * 最近激活时间
     * @param actDate
     */
    public void setActDate(Date actDate){
        this.actDate = actDate;
    }

    /**
     * 最近激活时间
     * @return
     */
    public Date getActDate(){
        return actDate;
    }
    /**
     * 粉丝电话
     * @param fansPhone
     */
    public void setFansPhone(String fansPhone){
        this.fansPhone = fansPhone;
    }

    /**
     * 粉丝电话
     * @return
     */
    public String getFansPhone(){
        return fansPhone;
    }

    /**
     * 取消关注时间
     * @param cancalDate
     */
    public void setCancalDate(Date cancalDate){
        this.cancalDate = cancalDate;
    }

    /**
     * 取消关注时间
     * @return
     */
    public Date getCancalDate(){
        return cancalDate;
    }
    /**
     * 创建时间
     * @param createDate
     */
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    /**
     * 创建时间
     * @return
     */
    public Date getCreateDate(){
        return createDate;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getGuiRemark() {
        return guiRemark;
    }

    public void setGuiRemark(String guiRemark) {
        this.guiRemark = guiRemark;
    }

}
