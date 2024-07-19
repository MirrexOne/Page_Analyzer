package hexlet.code.dto;

public class BasePage {

    private String flash;
    private String flashType;

    /**
     * Get flash message.
     * @return flash message
     */
    public String getFlash() {
        return flash;
    }

    /**
     * Set flash message.
     * @param flash - message for flash
     */
    public void setFlash(String flash) {
        this.flash = flash;
    }

    /**
     * Get flash type  status.
     * @return flash type status
     */
    public String getFlashType() {
        return flashType;
    }

    /**
     * Set flash type status.
     * @param flashType - flash type status
     */
    public void setFlashType(String flashType) {
        this.flashType = flashType;
    }
}
