package Day6;

import com.microsoft.playwright.Locator;

public class CustomeAssertions {
    private final Locator locator;

    private CustomeAssertions(Locator locator) {
        this.locator = locator;
    }

    public static CustomeAssertions assertThatCustom(Locator locator) {
        return new CustomeAssertions(locator);
    }

    //checking if element has class attribute with string in name
    public CustomeAssertions hasCssClass(String className) {
        String actualclass = locator.getAttribute("class");
        System.out.println("In Custom Assertion");
        System.out.println("Locator" + locator.toString());

        if (actualclass == null || !actualclass.contains(className)) {
            throw new AssertionError(
                    "Expected element to have class'" + className + "' but found :" + actualclass);
        }
        return this;
    }

    //Checking if element has Exact text
    public CustomeAssertions hasExactTxt(String exactText){
        String actualTxt = locator.innerText();
        System.out.println("In Custom Txt Assertion");
        System.out.println("Locator " + locator.toString());

        if(actualTxt ==null || !actualTxt.trim().equals(exactText.trim())){
            throw new AssertionError(
                    "Expected element to have text'" + exactText + "' but found: " + actualTxt);
        }
        return this;

    }
}