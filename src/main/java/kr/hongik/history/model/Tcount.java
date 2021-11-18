package kr.hongik.history.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;


@Data
@Alias("tcount")
public class Tcount {

    @SerializedName("type")
    public String type;

    @SerializedName("count")
    public int count;

    @SerializedName("tcountList")
    public List<Tcount> tcountList;

}
