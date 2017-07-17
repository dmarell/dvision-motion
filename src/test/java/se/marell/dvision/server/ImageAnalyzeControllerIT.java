/*
 * Created by Daniel Marell 15-06-24 09:13
 */
package se.marell.dvision.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.marell.dvision.api.ImageAnalyzeResponse;
import se.marell.dvision.client.DVisionSpringConfig;
import se.marell.dvision.client.ImageAnalyzeService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, DVisionSpringConfig.class},
        properties="server.port=23465", webEnvironment = DEFINED_PORT)
@TestPropertySource(properties = {
        "dvision.baseurl=http://localhost:23465"
})
public class ImageAnalyzeControllerIT {
    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Test
    public void shouldDetectMotion() throws Exception {
        ImageAnalyzeService service = beanFactory.createBean(ImageAnalyzeService.class);
        BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/image1.png"));
        BufferedImage image2 = ImageIO.read(this.getClass().getResourceAsStream("/image2.png"));

        {
            ResponseEntity<ImageAnalyzeResponse> r = service.analyzeImage("cam1", image1);
            assertThat(r.getStatusCode().is2xxSuccessful(), is(true));
            assertThat(r.getBody().getAreas().size(), is(0));
            assertThat(r.getBody().getImageSize().getWidth(), is(640));
            assertThat(r.getBody().getImageSize().getHeight(), is(480));
        }
        {
            ResponseEntity<ImageAnalyzeResponse> r = service.analyzeImage("cam1", image1);
            assertThat(r.getStatusCode().is2xxSuccessful(), is(true));
            assertThat(r.getBody().getAreas().size(), is(0));
            assertThat(r.getBody().getImageSize().getWidth(), is(640));
            assertThat(r.getBody().getImageSize().getHeight(), is(480));
        }
        {
            ResponseEntity<ImageAnalyzeResponse> r = service.analyzeImage("cam1", image2);
            assertThat(r.getStatusCode().is2xxSuccessful(), is(true));
            assertThat(r.getBody().getAreas().get("motion").size(), greaterThan(0));
            assertThat(r.getBody().getImageSize().getWidth(), is(640));
            assertThat(r.getBody().getImageSize().getHeight(), is(480));
        }
    }
}