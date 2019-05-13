package no.statkart.wsclient.stedsnavn;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class StedsnavnBobleIdTest {

    public void equals() {
        StedsnavnBobleId.StedsnavnId stedsnavnId1 = new StedsnavnBobleId.StedsnavnId("789");
        StedsnavnBobleId.StedsnavnId stedsnavnId2 = new StedsnavnBobleId.StedsnavnId("789");
        assertThat(stedsnavnId1).isEqualTo(stedsnavnId2);
    }

    public void notEquals() {
        StedsnavnBobleId.StedsnavnId stedsnavnId1 = new StedsnavnBobleId.StedsnavnId("789");
        StedsnavnBobleId.StedsnavnId stedsnavnId2 = new StedsnavnBobleId.StedsnavnId("790");
        assertThat(stedsnavnId1).isNotEqualTo(stedsnavnId2);

        StedsnavnBobleId.StedId stedId = new StedsnavnBobleId.StedId("789");
        assertThat(stedId).isNotEqualTo(stedsnavnId1);
    }

}
