import java.time.LocalTime;

public class UserInput {

    public static LocalTime parseLocalTime(String str) {
        String hour = "0";
        String minute = "0";

        String newString = str.strip();

        if (newString.contains(".") || newString.contains(" ") || newString.contains(":")) {
            if ( newString.length() == 4 ) {
                hour = newString.substring(0, 1);
                minute = newString.substring(2, 4);
            } else if ( newString.length() == 5 ) {
                hour = newString.substring(0, 2);
                minute = newString.substring(3, 5);
            }
        } else {
            if ( newString.length() == 3 ) {
                hour = newString.substring(0, 1);
                minute = newString.substring(1, 3);
            } else if ( newString.length() == 4 ) {
                hour = newString.substring(0, 2);
                minute = newString.substring(2, 4);
            }
        }

        return LocalTime.of(UserInput.parseInteger(hour), UserInput.parseInteger(minute));
    }

    public static String parseString(String str) {
		return str.strip();
	}

	public static Double parseDouble(String str) {
		return Double.parseDouble(str.strip());
	}

	public static Integer parseInteger(String str) {
		return Integer.parseInt(str.strip());
	}

	public static Boolean isQuit(String str) {
        return (str.equalsIgnoreCase("quit") ||
                str.equalsIgnoreCase("afslut") ||
                str.equalsIgnoreCase("luk") ||
                str.equalsIgnoreCase("stop") ||
                str.equalsIgnoreCase("exit") ||
                str.equalsIgnoreCase("end") ||
                str.equalsIgnoreCase("q")
        );
    }

    public static Boolean isExtra(String str) {
        return ( str.toLowerCase().contains("+") ||
                str.toLowerCase().contains("plus") ||
                str.toLowerCase().contains("ekstra") ||
                str.toLowerCase().contains("extra") ||
                str.toLowerCase().contains("add") ||
                str.toLowerCase().contains("more") ||
                str.toLowerCase().contains("xtra") ||
                str.toLowerCase().contains("mere") ||
                str.toLowerCase().contains("tilføj")
        );
    }
}
