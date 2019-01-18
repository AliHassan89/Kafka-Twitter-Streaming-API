package org.interview.properties;

import com.jparams.tester.ToStringTester;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class CrawlerPropertiesTest {

    @Test
    public void shouldCheckEqualsAndHashCodeContract() {
        EqualsVerifier.forClass(CrawlerProperties.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    public void shouldCheckToString() {
        ToStringTester.forClass(CrawlerProperties.class)
                .verify();
    }

}
