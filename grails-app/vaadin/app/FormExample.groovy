package app;

import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * This example demonstrates the most important features of the Form component:
 * binding Form to a JavaBean so that form fields are automatically generated
 * from the bean properties, creation of custom field editors using a
 * FieldFactory, customizing the form without FieldFactory, buffering
 * (commit/discard) and validation. Please note that the example is quite a bit
 * more complex than real use, as it tries to demonstrate more features than
 * needed in general case.
 */
@SuppressWarnings("serial")
public class FormExample extends CustomComponent {

    static final String cities = [ "Amsterdam", "Berlin", "Helsinki",
        "Hong Kong", "London", "Luxemburg", "New York", "Oslo", "Paris",
        "Rome", "Stockholm", "Tokyo", "Turku" ];

    /** Compose the demo. */
    public FormExample() {

        // Example data model
        final Address dataModel = new Address();
        Button peekDataModelState = new Button("Show the data model state",
                new Button.ClickListener() {

                    public void buttonClick(ClickEvent event) {
                        getWindow().showNotification(
                                dataModel.getAddressAsText());
                    }
                });

        // Example form
        final AddressForm form = new AddressForm("Contact Information");
        form.setDataSource(dataModel);
        form
                .setDescription("Please enter valid name and address. Fields marked with * are required. "
                + "If you try to commit with invalid values, a form error message is displayed. "
                + "(Address is required but failing to give it a value does not display an error.)");

        // Layout the example
        VerticalLayout root = new VerticalLayout();
        root.setMargin(true);
        root.setSpacing(true);
        root.addComponent(form);
        root.addComponent(peekDataModelState);
        setCompositionRoot(root);
    }

    public static class AddressForm extends Form {
        public AddressForm(String caption) {

            setCaption(caption);

            // Use custom field factory to modify the defaults on how the
            // components are created
            setFormFieldFactory(new MyFieldFactory());

            // Add Commit and Discard controls to the form.

            Button commit = new Button("Save", new ClickListener() {

                public void buttonClick(ClickEvent event) {
                    try {
                        commit();
                    } catch (InvalidValueException e) {
                        // Failed to commit. The validation errors are
                        // automatically shown to the user.
                    }
                }

            });

            Button discard = new Button("Reset");
            HorizontalLayout footer = new HorizontalLayout();
            footer.addComponent(commit);
            footer.addComponent(discard);
            setFooter(footer);
        }

        public void setDataSource(Address dataModel) {

            // Set the form to edit given datamodel by converting pojo used as
            // the datamodel to Item
            setItemDataSource(new BeanItem(dataModel));

            // Ensure that the fields are shown in correct order as the
            // datamodel does not force any specific order.
            setVisibleItemProperties([ "name", "streetAddress",
                "postalCode", "city"]);

            // For examples sake, customize some of the form fields directly
            // here. The alternative way is to use custom field factory as shown
            // above.
            getField("name").setRequired(true);
            getField("name").setDescription("Please enter name");
            getField("name").setRequiredError("Name is missing");
            getField("streetAddress").setRequired(true); // No error message
            getField("streetAddress").setDescription(
                    "Please enter street adderss.");
            getField("postalCode").setRequired(true); // No error message
            getField("postalCode").setDescription(
                    "Please enter postal code. Example: 12345.");
            replaceWithSelect("city", cities, cities).setNewItemsAllowed(true);
            getField("city")
                    .setDescription(
                    "Select city from list or type it. City field is not required.");

            // Set the form to act immediately on user input. This is
            // automatically transports data between the client and the server
            // to do server-side validation.
            setImmediate(true);

            // Enable buffering so that commit() must be called for the form
            // before input is written to the data. (Form input is not written
            // immediately through to the underlying object.)
            setWriteThrough(false);
        }
    }

    /**
     * This is example on how to customize field creation. Any kind of field
     * components could be created on the fly.
     */
    static class MyFieldFactory extends DefaultFieldFactory {

        @Override
        public Field createField(Item item, Object propertyId,
                                 Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);

            if ("postalCode".equals(propertyId)) {
                ((TextField) field).setColumns(5);
                field.addValidator(new PostalCodeValidator());
            }

            return field;
        }

    }

    /**
     * This is an example of how to create a custom validator for automatic
     * input validation.
     */
    static class PostalCodeValidator implements Validator {

        public boolean isValid(Object value) {
            if (value == null || !(value instanceof String)) {
                return false;
            }

            return ((String) value).matches("[0-9]{5}");
        }

        public void validate(Object value) throws InvalidValueException {
            if (!isValid(value)) {
                throw new InvalidValueException(
                        "Postal code must be a five digit number.");
            }
        }
    }

    /**
     * Contact information data model created as POJO. Note that in many cases
     * it would be a good idea to implement Item -interface for the datamodel to
     * make it directly bindable to form (without BeanItem wrapper)
     */
    public static class Address {
        String name = "";
        String streetAddress = "";
        String postalCode = "";
        String city;

        public String getAddressAsText() {
            return name + "\n" + streetAddress + "\n" + postalCode + " " + (city == null ? "" : city);
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setStreetAddress(String address) {
            streetAddress = address;
        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return city;
        }
    }

}
