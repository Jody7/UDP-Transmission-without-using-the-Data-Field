public class MainInterclass {
    // used for cross-class communication and stuff... could do better..
    public static String total = "";
    public static boolean stop = false;


    public static void add1(){
        total = total + "1";
    }
    public static void add0(){
        total = total + "0";
    }

    public static void stop(){
        stop = true;
    }

    public static String convert(String b){

        b = b.replaceAll("(.{8})(?!$)", "$1 ");
        String text = binaryToString(b);
        return text;
    }

    public static String binaryToString( String s ) {
        String[] ss = s.split( " " );
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < ss.length; i++ ) {
            sb.append( (char)Integer.parseInt( ss[i], 2 ) );
        }
        return sb.toString();
    }
}
