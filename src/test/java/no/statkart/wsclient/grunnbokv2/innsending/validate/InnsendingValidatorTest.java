package no.statkart.wsclient.grunnbokv2.innsending.validate;

import no.statkart.wsclient.grunnbokv2.innsending.domene.Forsendelse;
import no.statkart.wsclient.grunnbokv2.innsending.domene.builder.forsendelse.ForsendelseBuilder;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.testng.annotations.Test;

public class InnsendingValidatorTest {


    @Test
    public void testTomForsendelseV2GirValideringsfeil() {
        final ForsendelseBuilder forsendelseBuilder = ForsendelseBuilder.aForsendelse();
        final Forsendelse forsendelse = forsendelseBuilder.build();

        Assertions.assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
                @Override
                public void call() throws Throwable {
                    InnsendingValidator.validateForsendelseXml(forsendelse);
                }
            }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("\"http://kartverket.no/grunnbok/wsapi/v2/domain/innsending\":forsendelsesreferanse}' is expected.");
    }

}
