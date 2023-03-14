package forms;

public class LeftMenu {
    private AlertsFrameWindowsForm alertsFrameWindowsForm;
    private ElementsForm elementsForm;

    public AlertsFrameWindowsForm getAlertsFrameWindowsForm() {
        if (alertsFrameWindowsForm == null) {
            alertsFrameWindowsForm = new AlertsFrameWindowsForm();
        }
        return alertsFrameWindowsForm;
    }

    public ElementsForm getElementsForm() {
        if (elementsForm == null) {
            elementsForm = new ElementsForm();
        }
        return elementsForm;
    }

}
