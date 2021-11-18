package kr.hongik.history.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;


@Data
@Alias("hcount")
public class Hcount {

    @SerializedName("one")
    public int one;

    @SerializedName("two")
    public int two;

    @SerializedName("three")
    public int three;

    @SerializedName("four")
    public int four;

    @SerializedName("five")
    public int five;

    @SerializedName("six")
    public int six;

    @SerializedName("hcountList")
    public List<Hcount> hcountList;
}
