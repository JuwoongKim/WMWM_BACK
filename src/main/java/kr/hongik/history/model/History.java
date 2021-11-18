package kr.hongik.history.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Data
@Alias("history")
public class History {

    @SerializedName("seq")
    public int seq;

    @SerializedName("loginId")
    public String loginId;

    @SerializedName("subLoginId")
    public String subLoginId;

    @SerializedName("type")
    public String type;

    @SerializedName("latitude")
    public String latitude;

    @SerializedName("longitude")
    public String longitude;
    
    @SerializedName("regDt")
    public String regDt;

    @SerializedName("historyList")
    public List<History> historyList;
    
    @SerializedName("day")
    public String day;

    @SerializedName("beforeDay")
    public String beforeDay;

}