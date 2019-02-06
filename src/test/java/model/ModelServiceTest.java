package test.java.model;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import main.java.model.ModelService;
import main.java.model.Track;
import main.java.model.Tracks;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ModelServiceTest {
	@Autowired
	private ModelService ms;

	@Test
	public void getTracks() {
		Assert.assertTrue(ms!=null);
	}
}
