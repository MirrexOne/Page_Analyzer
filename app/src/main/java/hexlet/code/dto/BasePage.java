package hexlet.code.dto;

public class BasePage {
    private String flash;
    private String flashType;

    /**
     * Get flash message.
     * @return flash
     */
    public String getFlash() {
        return flash;
    }

    /**
     * Set flash message.
     * @param flash
     */
    public void setFlash(String flash) {
        this.flash = flash;
    }

    /**
     * Get flash type message.
     * @return flash type
     */
    public String getFlashType() {
        return flashType;
    }

    /**
     * Set flash type message.
     * @param flashType
     */
    public void setFlashType(String flashType) {
        this.flashType = flashType;
    }
}
