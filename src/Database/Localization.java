package Database;

import java.util.Locale;

/**
 * This public Localization class returns the text in the language of the location of the user.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class Localization {

    String locale = Locale.getDefault().toString().substring( 0, 2 );

    public String getLoginText() {
        String result;
        switch  (locale) {
            case "fr" :
                result = "Connexion";
                break;
            default:
                result = "Login";
        }
        return result;
    }

    public String getTitleText() {
        String result;
        switch (locale) {
            case "fr" :
                result = "Application de planification";
                break;
            default:
                result = "Scheduling Application";
        }
        return result;
    }

    public String getUsernameText() {
        String result;
        switch (locale) {
            case "fr" :
                result = "Usario";
                break;
            default:
                result = "Username";
        }
        return result;
    }

    public String getPasswordText() {
        String result;
        switch (locale) {
            case "fr" :
                result = "Mot de passe";
                break;
            default:
                result = "Password";
        }
        return result;
    }

    public String getWarningMessageText() {
        String result;
        switch (locale) {
            case "fr" :
                result = "Les informations d'identification fournies ne sont pas valides.";
                break;
            default:
                result = "The credentials provided are invalid.";
        }
        return result;
    }

    public String getWarningTitleText() {
        String result;
        switch (locale) {
            case "fr" :
                result = "Les informations d'identification invalides.";
                break;
            default:
                result = "Invalid Credentials.";
        }
        return result;

    }

    public String getOkText() {
        String result;
        switch (locale) {
            case "fr" :
                result = "d'accord";
                break;
            default:
                result = "OK";
        }
        return result;
    }

}
