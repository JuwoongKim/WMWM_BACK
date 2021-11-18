package kr.hongik.history.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("location")
public class Location {

    @SerializedName("latitude")
    public String latitude;

    @SerializedName("longitude")
    public String longitude;

    @SerializedName("locationList")
    private List<Location> locationList;

}
