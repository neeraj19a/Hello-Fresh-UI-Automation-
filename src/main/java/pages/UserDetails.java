package pages;

import com.github.javafaker.Faker;
import utils.Log;

//*********This Class uses "Faker Library" to generate random Users to register on the site*********/
public class UserDetails {

    Faker faker = new Faker();

    public String getFirstName() {
        //Generating the first name
        String firstName = faker.name().firstName();
        return firstName;
    }

    public String getLastName() {
        //Generating last name
        String lastName = faker.name().lastName();
        return lastName;
    }

    public String getPassword() {
        //Generating password
        String pwd = faker.internet().password();
        return pwd;
    }

    public String getCompanyData() {
        //Generating Company Name
        String companyname = faker.company().name();
        return companyname;
    }


    public String getCompanyAddress11() {
        //Generating Company Address
        String companyAddress = faker.address().buildingNumber();
        return companyAddress;
    }

    public String getCompanyAddress12() {
        //Generating Company Address
        String companyAddress = faker.address().buildingNumber();
        return companyAddress;
    }

    public String getCity() {
        //Generating City
        String city = faker.address().cityName();
        return city;
    }


    public String getState() {
        //Generating State Value
        String state = faker.address().state();
        Log.info("State value-->" + state);
        return state;
    }

    public String getPostalCode() {
        //Generating PostalCode
        String postalCode = faker.address().zipCode();
        while (postalCode.contains("-")) {
            postalCode = faker.address().zipCode();
        }
        Log.info("postalCode value-->" + postalCode);
        return postalCode;
    }

    public String getOtherInfo() {
        //Generating OtherInfo
        String otherInfo = faker.random().toString();
        return otherInfo;
    }

    public String getPhoneNumber() {
        //Generating PhoneNumber
        String phoneNumber = faker.phoneNumber().extension();

        Log.info("phoneNumber value-->" + phoneNumber);
        return phoneNumber;
    }

    public String getMobileNumber() {
        //Generating MobileNumber
        String mobileNumber = faker.phoneNumber().cellPhone();
        return mobileNumber;
    }

    public String getAlias() {
        //Generating Alias
        String alias = faker.name().firstName();
        return alias;
    }

}