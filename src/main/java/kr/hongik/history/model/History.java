package kr.hongik.history.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
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
    
    @SerializedName("day")
    public String day;

    @SerializedName("beforeDay")
    public String beforeDay;
    
	private String regDt;		//작성자

}