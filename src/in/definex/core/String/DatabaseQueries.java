package in.definex.core.String;

/**
 * Created by adam_ on 03-12-2017.
 */
public class DatabaseQueries {


    public static final String clientTable = "client";
    public static final String clientName = "name";
    public static final String clientGUID = "groupUID";
    public static final String clientRole = "role";
    public static final String clientCreateTable =
            "CREATE TABLE "+clientTable+" ("+clientName+" VARCHAR(30), "+clientGUID+" VARCHAR(30), "+clientRole+" INT)";

    public static final String clientInsert =
            "INSERT INTO "+clientTable+" VALUES (?,?,?)";

    public static final String clientSelectWhere =
            "SELECT * FROM "+clientTable+" WHERE "+clientName+" = ? AND "+clientGUID+" = ?";

    public static final String clientRoleUpdate =
            "UPDATE "+clientTable+" SET "+clientRole+" = ? WHERE "+clientName+" = ? AND "+clientGUID+" = ?";

    public static final String clientGetWithRole =
            "SELECT * FROM "+clientTable+" WHERE "+clientRole+" = ? AND "+clientGUID+" = ?";

    public static final String dropClient = "DROP TABLE "+clientTable;

    public static final String groupTable = "chat_group";
    public static final String groupUID = "groupuid";
    public static final String groupMyFeature = "myfeatures";

    public static final String groupCreateTable =
            "CREATE TABLE "+groupTable+" ("+groupUID+" VARCHAR(30) PRIMARY KEY, "+groupMyFeature+" TEXT)";

    public static final String groupInsert =
            "INSERT INTO "+groupTable+" VALUES (?,?)";

    public static  final String groupSelectAll =
            "SELECT * FROM "+groupTable;

    public static final String groupFeatureUpdate =
            "UPDATE "+groupTable+" SET "+groupMyFeature+" = ? WHERE "+groupUID+" = ?";

    public static final String dropGroup = "DROP TABLE "+groupTable;

}
