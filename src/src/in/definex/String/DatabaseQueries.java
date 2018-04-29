package in.definex.String;

/**
 * DatabaseQuries
 * String quires used by Database Manager
 *
 * Created by adam_ on 03-12-2017.
 */
public class DatabaseQueries {




    public static final String groupTable = "chat_group";
    public static final String groupUID = "groupuid";
    public static final String groupMyFeature = "myfeatures";

    public static final String groupCreateTable =
            "CREATE TABLE "+groupTable+" ("+groupUID+" VARCHAR(30) PRIMARY KEY, "+groupMyFeature+" TEXT)";

    public static final String dropGroupTable =
            "DROP TABLE "+groupTable;

    public static final String groupInsert =
            "INSERT INTO "+groupTable+" VALUES (?,?)";

    public static  final String groupSelectAll =
            "SELECT * FROM "+groupTable;

    public static final String groupDelete =
            "DELETE FROM "+groupTable+" WHERE "+groupUID+"= ?";


    public static final String groupFeatureUpdate =
            "UPDATE "+groupTable+" SET "+groupMyFeature+" = ? WHERE "+groupUID+" = ?";

}
