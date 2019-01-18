package org.interview.properties;

import com.jparams.tester.ToStringTester;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class OauthPropertiesTest {

    @Test
    public void shouldCheckEqualsAndHashCodeContract() {
        EqualsVerifier.forClass(OauthProperties.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    public void shouldCheckToString() {
        ToStringTester.forClass(OauthProperties.class)
                .verify();
    }

}
