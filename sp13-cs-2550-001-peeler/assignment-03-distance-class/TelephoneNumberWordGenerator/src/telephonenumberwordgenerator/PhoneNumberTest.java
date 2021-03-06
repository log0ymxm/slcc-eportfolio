package telephonenumberwordgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import junit.framework.*;
import junit.extensions.*;

/**
 *
 * @author nrub
 */
public class PhoneNumberTest extends TestCase
{

	/**
	 * Test of setNumber method, of class PhoneNumber.
	 */
	public void testSetNumber()
	{
		System.out.println("setNumber");
		PhoneNumber instance = new PhoneNumber();

		// invalid numbers
		try
		{
			instance.setNumber("");
		}
		catch (Exception e)
		{
			assertEquals("no number", "Your phone number must contain at least 7 digits.", e.getMessage());
		}
		try
		{
			instance.setNumber("1 (805) 440-0925");
		}
		catch (Exception e)
		{
			assertEquals("has a 0", "We cannot work with phone numbers that contain 0 or 1", e.getMessage());
		}
		try
		{
			instance.setNumber("440-0925");
		}
		catch (Exception e)
		{
			assertEquals("has a 0", "We cannot work with phone numbers that contain 0 or 1", e.getMessage());
		}
		try
		{
			instance.setNumber("4400925");
		}
		catch (Exception e)
		{
			assertEquals("has a 0", "We cannot work with phone numbers that contain 0 or 1", e.getMessage());
		}
		try
		{
			instance.setNumber("4421925");
		}
		catch (Exception e)
		{
			assertEquals("has a 1", "We cannot work with phone numbers that contain 0 or 1", e.getMessage());
		}
		try
		{
			instance.setNumber("442192592");
		}
		catch (Exception e)
		{
			assertEquals("more than 7 but still has a 1", "We cannot work with phone numbers that contain 0 or 1", e.getMessage());
		}
		try
		{
			instance.setNumber("1 (805) 442/2925");
		}
		catch (Exception e)
		{
			assertEquals("sanitized & normalized form still contains invalid characters", "Number contains invalid characters", e.getMessage());
		}

		// valid numbers
		try
		{
			instance.setNumber("1 (805) 442-3925");
			assertEquals("4423925", instance.getNumber());
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
		try
		{
			instance.setNumber("442-3925");
			assertEquals("4423925", instance.getNumber());
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
		try
		{
			instance.setNumber("4423925");
			assertEquals("4423925", instance.getNumber());
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
		try
		{
			instance.setNumber("4959529384");
			assertEquals("more than 7, takes last 7 numbers", "9529384", instance.getNumber());
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
		try
		{
			instance.setNumber("1 (805) 445-29492");
			assertEquals("more than 7 with area & country code, takes last 7 numbers", "4529492", instance.getNumber());
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}

		System.out.println("setNumber finished");
	}

	/**
	 * Test of getNumber method, of class PhoneNumber.
	 */
	public void testGetNumber()
	{
		System.out.println("getNumber");
		PhoneNumber instance = null;
		try
		{
			instance = new PhoneNumber("1 (805) 442-3925");
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
		String expResult = "4423925";
		String result = instance.getNumber();
		assertEquals("sets a number with country & area code", expResult, result);

		try
		{
			instance.setNumber("442-3925");
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
		result = instance.getNumber();
		assertEquals("sets a number with a dash", expResult, result);

		try
		{
			instance.setNumber("4423925");
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
		result = instance.getNumber();
		assertEquals("sets a vanilla number", expResult, result);

		System.out.println("getNumber finished");
	}

	/**
	 * Test of getWords method, of class PhoneNumber.
	 */
	public void testGetWords_0args()
	{
		System.out.println("getWords");
		PhoneNumber instance = new PhoneNumber("4422935");
		String[] expResult = new String[]
		{
			"GGAAWDJ", "HGAAWDJ", "IGAAWDJ", "GHAAWDJ", "HHAAWDJ", "IHAAWDJ", "GIAAWDJ", "HIAAWDJ", "IIAAWDJ", "GGBAWDJ", "HGBAWDJ", "IGBAWDJ", "GHBAWDJ", "HHBAWDJ", "IHBAWDJ", "GIBAWDJ", "HIBAWDJ", "IIBAWDJ", "GGCAWDJ", "HGCAWDJ", "IGCAWDJ", "GHCAWDJ", "HHCAWDJ", "IHCAWDJ", "GICAWDJ", "HICAWDJ", "IICAWDJ", "GGABWDJ", "HGABWDJ", "IGABWDJ", "GHABWDJ", "HHABWDJ", "IHABWDJ", "GIABWDJ", "HIABWDJ", "IIABWDJ", "GGBBWDJ", "HGBBWDJ", "IGBBWDJ", "GHBBWDJ", "HHBBWDJ", "IHBBWDJ", "GIBBWDJ", "HIBBWDJ", "IIBBWDJ", "GGCBWDJ", "HGCBWDJ", "IGCBWDJ", "GHCBWDJ", "HHCBWDJ", "IHCBWDJ", "GICBWDJ", "HICBWDJ", "IICBWDJ", "GGACWDJ", "HGACWDJ", "IGACWDJ", "GHACWDJ", "HHACWDJ", "IHACWDJ", "GIACWDJ", "HIACWDJ", "IIACWDJ", "GGBCWDJ", "HGBCWDJ", "IGBCWDJ", "GHBCWDJ", "HHBCWDJ", "IHBCWDJ", "GIBCWDJ", "HIBCWDJ", "IIBCWDJ", "GGCCWDJ", "HGCCWDJ", "IGCCWDJ", "GHCCWDJ", "HHCCWDJ", "IHCCWDJ", "GICCWDJ", "HICCWDJ", "IICCWDJ", "GGAAXDJ", "HGAAXDJ", "IGAAXDJ", "GHAAXDJ", "HHAAXDJ", "IHAAXDJ", "GIAAXDJ", "HIAAXDJ", "IIAAXDJ", "GGBAXDJ", "HGBAXDJ", "IGBAXDJ", "GHBAXDJ", "HHBAXDJ", "IHBAXDJ", "GIBAXDJ", "HIBAXDJ", "IIBAXDJ", "GGCAXDJ", "HGCAXDJ", "IGCAXDJ", "GHCAXDJ", "HHCAXDJ", "IHCAXDJ", "GICAXDJ", "HICAXDJ", "IICAXDJ", "GGABXDJ", "HGABXDJ", "IGABXDJ", "GHABXDJ", "HHABXDJ", "IHABXDJ", "GIABXDJ", "HIABXDJ", "IIABXDJ", "GGBBXDJ", "HGBBXDJ", "IGBBXDJ", "GHBBXDJ", "HHBBXDJ", "IHBBXDJ", "GIBBXDJ", "HIBBXDJ", "IIBBXDJ", "GGCBXDJ", "HGCBXDJ", "IGCBXDJ", "GHCBXDJ", "HHCBXDJ", "IHCBXDJ", "GICBXDJ", "HICBXDJ", "IICBXDJ", "GGACXDJ", "HGACXDJ", "IGACXDJ", "GHACXDJ", "HHACXDJ", "IHACXDJ", "GIACXDJ", "HIACXDJ", "IIACXDJ", "GGBCXDJ", "HGBCXDJ", "IGBCXDJ", "GHBCXDJ", "HHBCXDJ", "IHBCXDJ", "GIBCXDJ", "HIBCXDJ", "IIBCXDJ", "GGCCXDJ", "HGCCXDJ", "IGCCXDJ", "GHCCXDJ", "HHCCXDJ", "IHCCXDJ", "GICCXDJ", "HICCXDJ", "IICCXDJ", "GGAAYDJ", "HGAAYDJ", "IGAAYDJ", "GHAAYDJ", "HHAAYDJ", "IHAAYDJ", "GIAAYDJ", "HIAAYDJ", "IIAAYDJ", "GGBAYDJ", "HGBAYDJ", "IGBAYDJ", "GHBAYDJ", "HHBAYDJ", "IHBAYDJ", "GIBAYDJ", "HIBAYDJ", "IIBAYDJ", "GGCAYDJ", "HGCAYDJ", "IGCAYDJ", "GHCAYDJ", "HHCAYDJ", "IHCAYDJ", "GICAYDJ", "HICAYDJ", "IICAYDJ", "GGABYDJ", "HGABYDJ", "IGABYDJ", "GHABYDJ", "HHABYDJ", "IHABYDJ", "GIABYDJ", "HIABYDJ", "IIABYDJ", "GGBBYDJ", "HGBBYDJ", "IGBBYDJ", "GHBBYDJ", "HHBBYDJ", "IHBBYDJ", "GIBBYDJ", "HIBBYDJ", "IIBBYDJ", "GGCBYDJ", "HGCBYDJ", "IGCBYDJ", "GHCBYDJ", "HHCBYDJ", "IHCBYDJ", "GICBYDJ", "HICBYDJ", "IICBYDJ", "GGACYDJ", "HGACYDJ", "IGACYDJ", "GHACYDJ", "HHACYDJ", "IHACYDJ", "GIACYDJ", "HIACYDJ", "IIACYDJ", "GGBCYDJ", "HGBCYDJ", "IGBCYDJ", "GHBCYDJ", "HHBCYDJ", "IHBCYDJ", "GIBCYDJ", "HIBCYDJ", "IIBCYDJ", "GGCCYDJ", "HGCCYDJ", "IGCCYDJ", "GHCCYDJ", "HHCCYDJ", "IHCCYDJ", "GICCYDJ", "HICCYDJ", "IICCYDJ", "GGAAWEJ", "HGAAWEJ", "IGAAWEJ", "GHAAWEJ", "HHAAWEJ", "IHAAWEJ", "GIAAWEJ", "HIAAWEJ", "IIAAWEJ", "GGBAWEJ", "HGBAWEJ", "IGBAWEJ", "GHBAWEJ", "HHBAWEJ", "IHBAWEJ", "GIBAWEJ", "HIBAWEJ", "IIBAWEJ", "GGCAWEJ", "HGCAWEJ", "IGCAWEJ", "GHCAWEJ", "HHCAWEJ", "IHCAWEJ", "GICAWEJ", "HICAWEJ", "IICAWEJ", "GGABWEJ", "HGABWEJ", "IGABWEJ", "GHABWEJ", "HHABWEJ", "IHABWEJ", "GIABWEJ", "HIABWEJ", "IIABWEJ", "GGBBWEJ", "HGBBWEJ", "IGBBWEJ", "GHBBWEJ", "HHBBWEJ", "IHBBWEJ", "GIBBWEJ", "HIBBWEJ", "IIBBWEJ", "GGCBWEJ", "HGCBWEJ", "IGCBWEJ", "GHCBWEJ", "HHCBWEJ", "IHCBWEJ", "GICBWEJ", "HICBWEJ", "IICBWEJ", "GGACWEJ", "HGACWEJ", "IGACWEJ", "GHACWEJ", "HHACWEJ", "IHACWEJ", "GIACWEJ", "HIACWEJ", "IIACWEJ", "GGBCWEJ", "HGBCWEJ", "IGBCWEJ", "GHBCWEJ", "HHBCWEJ", "IHBCWEJ", "GIBCWEJ", "HIBCWEJ", "IIBCWEJ", "GGCCWEJ", "HGCCWEJ", "IGCCWEJ", "GHCCWEJ", "HHCCWEJ", "IHCCWEJ", "GICCWEJ", "HICCWEJ", "IICCWEJ", "GGAAXEJ", "HGAAXEJ", "IGAAXEJ", "GHAAXEJ", "HHAAXEJ", "IHAAXEJ", "GIAAXEJ", "HIAAXEJ", "IIAAXEJ", "GGBAXEJ", "HGBAXEJ", "IGBAXEJ", "GHBAXEJ", "HHBAXEJ", "IHBAXEJ", "GIBAXEJ", "HIBAXEJ", "IIBAXEJ", "GGCAXEJ", "HGCAXEJ", "IGCAXEJ", "GHCAXEJ", "HHCAXEJ", "IHCAXEJ", "GICAXEJ", "HICAXEJ", "IICAXEJ", "GGABXEJ", "HGABXEJ", "IGABXEJ", "GHABXEJ", "HHABXEJ", "IHABXEJ", "GIABXEJ", "HIABXEJ", "IIABXEJ", "GGBBXEJ", "HGBBXEJ", "IGBBXEJ", "GHBBXEJ", "HHBBXEJ", "IHBBXEJ", "GIBBXEJ", "HIBBXEJ", "IIBBXEJ", "GGCBXEJ", "HGCBXEJ", "IGCBXEJ", "GHCBXEJ", "HHCBXEJ", "IHCBXEJ", "GICBXEJ", "HICBXEJ", "IICBXEJ", "GGACXEJ", "HGACXEJ", "IGACXEJ", "GHACXEJ", "HHACXEJ", "IHACXEJ", "GIACXEJ", "HIACXEJ", "IIACXEJ", "GGBCXEJ", "HGBCXEJ", "IGBCXEJ", "GHBCXEJ", "HHBCXEJ", "IHBCXEJ", "GIBCXEJ", "HIBCXEJ", "IIBCXEJ", "GGCCXEJ", "HGCCXEJ", "IGCCXEJ", "GHCCXEJ", "HHCCXEJ", "IHCCXEJ", "GICCXEJ", "HICCXEJ", "IICCXEJ", "GGAAYEJ", "HGAAYEJ", "IGAAYEJ", "GHAAYEJ", "HHAAYEJ", "IHAAYEJ", "GIAAYEJ", "HIAAYEJ", "IIAAYEJ", "GGBAYEJ", "HGBAYEJ", "IGBAYEJ", "GHBAYEJ", "HHBAYEJ", "IHBAYEJ", "GIBAYEJ", "HIBAYEJ", "IIBAYEJ", "GGCAYEJ", "HGCAYEJ", "IGCAYEJ", "GHCAYEJ", "HHCAYEJ", "IHCAYEJ", "GICAYEJ", "HICAYEJ", "IICAYEJ", "GGABYEJ", "HGABYEJ", "IGABYEJ", "GHABYEJ", "HHABYEJ", "IHABYEJ", "GIABYEJ", "HIABYEJ", "IIABYEJ", "GGBBYEJ", "HGBBYEJ", "IGBBYEJ", "GHBBYEJ", "HHBBYEJ", "IHBBYEJ", "GIBBYEJ", "HIBBYEJ", "IIBBYEJ", "GGCBYEJ", "HGCBYEJ", "IGCBYEJ", "GHCBYEJ", "HHCBYEJ", "IHCBYEJ", "GICBYEJ", "HICBYEJ", "IICBYEJ", "GGACYEJ", "HGACYEJ", "IGACYEJ", "GHACYEJ", "HHACYEJ", "IHACYEJ", "GIACYEJ", "HIACYEJ", "IIACYEJ", "GGBCYEJ", "HGBCYEJ", "IGBCYEJ", "GHBCYEJ", "HHBCYEJ", "IHBCYEJ", "GIBCYEJ", "HIBCYEJ", "IIBCYEJ", "GGCCYEJ", "HGCCYEJ", "IGCCYEJ", "GHCCYEJ", "HHCCYEJ", "IHCCYEJ", "GICCYEJ", "HICCYEJ", "IICCYEJ", "GGAAWFJ", "HGAAWFJ", "IGAAWFJ", "GHAAWFJ", "HHAAWFJ", "IHAAWFJ", "GIAAWFJ", "HIAAWFJ", "IIAAWFJ", "GGBAWFJ", "HGBAWFJ", "IGBAWFJ", "GHBAWFJ", "HHBAWFJ", "IHBAWFJ", "GIBAWFJ", "HIBAWFJ", "IIBAWFJ", "GGCAWFJ", "HGCAWFJ", "IGCAWFJ", "GHCAWFJ", "HHCAWFJ", "IHCAWFJ", "GICAWFJ", "HICAWFJ", "IICAWFJ", "GGABWFJ", "HGABWFJ", "IGABWFJ", "GHABWFJ", "HHABWFJ", "IHABWFJ", "GIABWFJ", "HIABWFJ", "IIABWFJ", "GGBBWFJ", "HGBBWFJ", "IGBBWFJ", "GHBBWFJ", "HHBBWFJ", "IHBBWFJ", "GIBBWFJ", "HIBBWFJ", "IIBBWFJ", "GGCBWFJ", "HGCBWFJ", "IGCBWFJ", "GHCBWFJ", "HHCBWFJ", "IHCBWFJ", "GICBWFJ", "HICBWFJ", "IICBWFJ", "GGACWFJ", "HGACWFJ", "IGACWFJ", "GHACWFJ", "HHACWFJ", "IHACWFJ", "GIACWFJ", "HIACWFJ", "IIACWFJ", "GGBCWFJ", "HGBCWFJ", "IGBCWFJ", "GHBCWFJ", "HHBCWFJ", "IHBCWFJ", "GIBCWFJ", "HIBCWFJ", "IIBCWFJ", "GGCCWFJ", "HGCCWFJ", "IGCCWFJ", "GHCCWFJ", "HHCCWFJ", "IHCCWFJ", "GICCWFJ", "HICCWFJ", "IICCWFJ", "GGAAXFJ", "HGAAXFJ", "IGAAXFJ", "GHAAXFJ", "HHAAXFJ", "IHAAXFJ", "GIAAXFJ", "HIAAXFJ", "IIAAXFJ", "GGBAXFJ", "HGBAXFJ", "IGBAXFJ", "GHBAXFJ", "HHBAXFJ", "IHBAXFJ", "GIBAXFJ", "HIBAXFJ", "IIBAXFJ", "GGCAXFJ", "HGCAXFJ", "IGCAXFJ", "GHCAXFJ", "HHCAXFJ", "IHCAXFJ", "GICAXFJ", "HICAXFJ", "IICAXFJ", "GGABXFJ", "HGABXFJ", "IGABXFJ", "GHABXFJ", "HHABXFJ", "IHABXFJ", "GIABXFJ", "HIABXFJ", "IIABXFJ", "GGBBXFJ", "HGBBXFJ", "IGBBXFJ", "GHBBXFJ", "HHBBXFJ", "IHBBXFJ", "GIBBXFJ", "HIBBXFJ", "IIBBXFJ", "GGCBXFJ", "HGCBXFJ", "IGCBXFJ", "GHCBXFJ", "HHCBXFJ", "IHCBXFJ", "GICBXFJ", "HICBXFJ", "IICBXFJ", "GGACXFJ", "HGACXFJ", "IGACXFJ", "GHACXFJ", "HHACXFJ", "IHACXFJ", "GIACXFJ", "HIACXFJ", "IIACXFJ", "GGBCXFJ", "HGBCXFJ", "IGBCXFJ", "GHBCXFJ", "HHBCXFJ", "IHBCXFJ", "GIBCXFJ", "HIBCXFJ", "IIBCXFJ", "GGCCXFJ", "HGCCXFJ", "IGCCXFJ", "GHCCXFJ", "HHCCXFJ", "IHCCXFJ", "GICCXFJ", "HICCXFJ", "IICCXFJ", "GGAAYFJ", "HGAAYFJ", "IGAAYFJ", "GHAAYFJ", "HHAAYFJ", "IHAAYFJ", "GIAAYFJ", "HIAAYFJ", "IIAAYFJ", "GGBAYFJ", "HGBAYFJ", "IGBAYFJ", "GHBAYFJ", "HHBAYFJ", "IHBAYFJ", "GIBAYFJ", "HIBAYFJ", "IIBAYFJ", "GGCAYFJ", "HGCAYFJ", "IGCAYFJ", "GHCAYFJ", "HHCAYFJ", "IHCAYFJ", "GICAYFJ", "HICAYFJ", "IICAYFJ", "GGABYFJ", "HGABYFJ", "IGABYFJ", "GHABYFJ", "HHABYFJ", "IHABYFJ", "GIABYFJ", "HIABYFJ", "IIABYFJ", "GGBBYFJ", "HGBBYFJ", "IGBBYFJ", "GHBBYFJ", "HHBBYFJ", "IHBBYFJ", "GIBBYFJ", "HIBBYFJ", "IIBBYFJ", "GGCBYFJ", "HGCBYFJ", "IGCBYFJ", "GHCBYFJ", "HHCBYFJ", "IHCBYFJ", "GICBYFJ", "HICBYFJ", "IICBYFJ", "GGACYFJ", "HGACYFJ", "IGACYFJ", "GHACYFJ", "HHACYFJ", "IHACYFJ", "GIACYFJ", "HIACYFJ", "IIACYFJ", "GGBCYFJ", "HGBCYFJ", "IGBCYFJ", "GHBCYFJ", "HHBCYFJ", "IHBCYFJ", "GIBCYFJ", "HIBCYFJ", "IIBCYFJ", "GGCCYFJ", "HGCCYFJ", "IGCCYFJ", "GHCCYFJ", "HHCCYFJ", "IHCCYFJ", "GICCYFJ", "HICCYFJ", "IICCYFJ", "GGAAWDK", "HGAAWDK", "IGAAWDK", "GHAAWDK", "HHAAWDK", "IHAAWDK", "GIAAWDK", "HIAAWDK", "IIAAWDK", "GGBAWDK", "HGBAWDK", "IGBAWDK", "GHBAWDK", "HHBAWDK", "IHBAWDK", "GIBAWDK", "HIBAWDK", "IIBAWDK", "GGCAWDK", "HGCAWDK", "IGCAWDK", "GHCAWDK", "HHCAWDK", "IHCAWDK", "GICAWDK", "HICAWDK", "IICAWDK", "GGABWDK", "HGABWDK", "IGABWDK", "GHABWDK", "HHABWDK", "IHABWDK", "GIABWDK", "HIABWDK", "IIABWDK", "GGBBWDK", "HGBBWDK", "IGBBWDK", "GHBBWDK", "HHBBWDK", "IHBBWDK", "GIBBWDK", "HIBBWDK", "IIBBWDK", "GGCBWDK", "HGCBWDK", "IGCBWDK", "GHCBWDK", "HHCBWDK", "IHCBWDK", "GICBWDK", "HICBWDK", "IICBWDK", "GGACWDK", "HGACWDK", "IGACWDK", "GHACWDK", "HHACWDK", "IHACWDK", "GIACWDK", "HIACWDK", "IIACWDK", "GGBCWDK", "HGBCWDK", "IGBCWDK", "GHBCWDK", "HHBCWDK", "IHBCWDK", "GIBCWDK", "HIBCWDK", "IIBCWDK", "GGCCWDK", "HGCCWDK", "IGCCWDK", "GHCCWDK", "HHCCWDK", "IHCCWDK", "GICCWDK", "HICCWDK", "IICCWDK", "GGAAXDK", "HGAAXDK", "IGAAXDK", "GHAAXDK", "HHAAXDK", "IHAAXDK", "GIAAXDK", "HIAAXDK", "IIAAXDK", "GGBAXDK", "HGBAXDK", "IGBAXDK", "GHBAXDK", "HHBAXDK", "IHBAXDK", "GIBAXDK", "HIBAXDK", "IIBAXDK", "GGCAXDK", "HGCAXDK", "IGCAXDK", "GHCAXDK", "HHCAXDK", "IHCAXDK", "GICAXDK", "HICAXDK", "IICAXDK", "GGABXDK", "HGABXDK", "IGABXDK", "GHABXDK", "HHABXDK", "IHABXDK", "GIABXDK", "HIABXDK", "IIABXDK", "GGBBXDK", "HGBBXDK", "IGBBXDK", "GHBBXDK", "HHBBXDK", "IHBBXDK", "GIBBXDK", "HIBBXDK", "IIBBXDK", "GGCBXDK", "HGCBXDK", "IGCBXDK", "GHCBXDK", "HHCBXDK", "IHCBXDK", "GICBXDK", "HICBXDK", "IICBXDK", "GGACXDK", "HGACXDK", "IGACXDK", "GHACXDK", "HHACXDK", "IHACXDK", "GIACXDK", "HIACXDK", "IIACXDK", "GGBCXDK", "HGBCXDK", "IGBCXDK", "GHBCXDK", "HHBCXDK", "IHBCXDK", "GIBCXDK", "HIBCXDK", "IIBCXDK", "GGCCXDK", "HGCCXDK", "IGCCXDK", "GHCCXDK", "HHCCXDK", "IHCCXDK", "GICCXDK", "HICCXDK", "IICCXDK", "GGAAYDK", "HGAAYDK", "IGAAYDK", "GHAAYDK", "HHAAYDK", "IHAAYDK", "GIAAYDK", "HIAAYDK", "IIAAYDK", "GGBAYDK", "HGBAYDK", "IGBAYDK", "GHBAYDK", "HHBAYDK", "IHBAYDK", "GIBAYDK", "HIBAYDK", "IIBAYDK", "GGCAYDK", "HGCAYDK", "IGCAYDK", "GHCAYDK", "HHCAYDK", "IHCAYDK", "GICAYDK", "HICAYDK", "IICAYDK", "GGABYDK", "HGABYDK", "IGABYDK", "GHABYDK", "HHABYDK", "IHABYDK", "GIABYDK", "HIABYDK", "IIABYDK", "GGBBYDK", "HGBBYDK", "IGBBYDK", "GHBBYDK", "HHBBYDK", "IHBBYDK", "GIBBYDK", "HIBBYDK", "IIBBYDK", "GGCBYDK", "HGCBYDK", "IGCBYDK", "GHCBYDK", "HHCBYDK", "IHCBYDK", "GICBYDK", "HICBYDK", "IICBYDK", "GGACYDK", "HGACYDK", "IGACYDK", "GHACYDK", "HHACYDK", "IHACYDK", "GIACYDK", "HIACYDK", "IIACYDK", "GGBCYDK", "HGBCYDK", "IGBCYDK", "GHBCYDK", "HHBCYDK", "IHBCYDK", "GIBCYDK", "HIBCYDK", "IIBCYDK", "GGCCYDK", "HGCCYDK", "IGCCYDK", "GHCCYDK", "HHCCYDK", "IHCCYDK", "GICCYDK", "HICCYDK", "IICCYDK", "GGAAWEK", "HGAAWEK", "IGAAWEK", "GHAAWEK", "HHAAWEK", "IHAAWEK", "GIAAWEK", "HIAAWEK", "IIAAWEK", "GGBAWEK", "HGBAWEK", "IGBAWEK", "GHBAWEK", "HHBAWEK", "IHBAWEK", "GIBAWEK", "HIBAWEK", "IIBAWEK", "GGCAWEK", "HGCAWEK", "IGCAWEK", "GHCAWEK", "HHCAWEK", "IHCAWEK", "GICAWEK", "HICAWEK", "IICAWEK", "GGABWEK", "HGABWEK", "IGABWEK", "GHABWEK", "HHABWEK", "IHABWEK", "GIABWEK", "HIABWEK", "IIABWEK", "GGBBWEK", "HGBBWEK", "IGBBWEK", "GHBBWEK", "HHBBWEK", "IHBBWEK", "GIBBWEK", "HIBBWEK", "IIBBWEK", "GGCBWEK", "HGCBWEK", "IGCBWEK", "GHCBWEK", "HHCBWEK", "IHCBWEK", "GICBWEK", "HICBWEK", "IICBWEK", "GGACWEK", "HGACWEK", "IGACWEK", "GHACWEK", "HHACWEK", "IHACWEK", "GIACWEK", "HIACWEK", "IIACWEK", "GGBCWEK", "HGBCWEK", "IGBCWEK", "GHBCWEK", "HHBCWEK", "IHBCWEK", "GIBCWEK", "HIBCWEK", "IIBCWEK", "GGCCWEK", "HGCCWEK", "IGCCWEK", "GHCCWEK", "HHCCWEK", "IHCCWEK", "GICCWEK", "HICCWEK", "IICCWEK", "GGAAXEK", "HGAAXEK", "IGAAXEK", "GHAAXEK", "HHAAXEK", "IHAAXEK", "GIAAXEK", "HIAAXEK", "IIAAXEK", "GGBAXEK", "HGBAXEK", "IGBAXEK", "GHBAXEK", "HHBAXEK", "IHBAXEK", "GIBAXEK", "HIBAXEK", "IIBAXEK", "GGCAXEK", "HGCAXEK", "IGCAXEK", "GHCAXEK", "HHCAXEK", "IHCAXEK", "GICAXEK", "HICAXEK", "IICAXEK", "GGABXEK", "HGABXEK", "IGABXEK", "GHABXEK", "HHABXEK", "IHABXEK", "GIABXEK", "HIABXEK", "IIABXEK", "GGBBXEK", "HGBBXEK", "IGBBXEK", "GHBBXEK", "HHBBXEK", "IHBBXEK", "GIBBXEK", "HIBBXEK", "IIBBXEK", "GGCBXEK", "HGCBXEK", "IGCBXEK", "GHCBXEK", "HHCBXEK", "IHCBXEK", "GICBXEK", "HICBXEK", "IICBXEK", "GGACXEK", "HGACXEK", "IGACXEK", "GHACXEK", "HHACXEK", "IHACXEK", "GIACXEK", "HIACXEK", "IIACXEK", "GGBCXEK", "HGBCXEK", "IGBCXEK", "GHBCXEK", "HHBCXEK", "IHBCXEK", "GIBCXEK", "HIBCXEK", "IIBCXEK", "GGCCXEK", "HGCCXEK", "IGCCXEK", "GHCCXEK", "HHCCXEK", "IHCCXEK", "GICCXEK", "HICCXEK", "IICCXEK", "GGAAYEK", "HGAAYEK", "IGAAYEK", "GHAAYEK", "HHAAYEK", "IHAAYEK", "GIAAYEK", "HIAAYEK", "IIAAYEK", "GGBAYEK", "HGBAYEK", "IGBAYEK", "GHBAYEK", "HHBAYEK", "IHBAYEK", "GIBAYEK", "HIBAYEK", "IIBAYEK", "GGCAYEK", "HGCAYEK", "IGCAYEK", "GHCAYEK", "HHCAYEK", "IHCAYEK", "GICAYEK", "HICAYEK", "IICAYEK", "GGABYEK", "HGABYEK", "IGABYEK", "GHABYEK", "HHABYEK", "IHABYEK", "GIABYEK", "HIABYEK", "IIABYEK", "GGBBYEK", "HGBBYEK", "IGBBYEK", "GHBBYEK", "HHBBYEK", "IHBBYEK", "GIBBYEK", "HIBBYEK", "IIBBYEK", "GGCBYEK", "HGCBYEK", "IGCBYEK", "GHCBYEK", "HHCBYEK", "IHCBYEK", "GICBYEK", "HICBYEK", "IICBYEK", "GGACYEK", "HGACYEK", "IGACYEK", "GHACYEK", "HHACYEK", "IHACYEK", "GIACYEK", "HIACYEK", "IIACYEK", "GGBCYEK", "HGBCYEK", "IGBCYEK", "GHBCYEK", "HHBCYEK", "IHBCYEK", "GIBCYEK", "HIBCYEK", "IIBCYEK", "GGCCYEK", "HGCCYEK", "IGCCYEK", "GHCCYEK", "HHCCYEK", "IHCCYEK", "GICCYEK", "HICCYEK", "IICCYEK", "GGAAWFK", "HGAAWFK", "IGAAWFK", "GHAAWFK", "HHAAWFK", "IHAAWFK", "GIAAWFK", "HIAAWFK", "IIAAWFK", "GGBAWFK", "HGBAWFK", "IGBAWFK", "GHBAWFK", "HHBAWFK", "IHBAWFK", "GIBAWFK", "HIBAWFK", "IIBAWFK", "GGCAWFK", "HGCAWFK", "IGCAWFK", "GHCAWFK", "HHCAWFK", "IHCAWFK", "GICAWFK", "HICAWFK", "IICAWFK", "GGABWFK", "HGABWFK", "IGABWFK", "GHABWFK", "HHABWFK", "IHABWFK", "GIABWFK", "HIABWFK", "IIABWFK", "GGBBWFK", "HGBBWFK", "IGBBWFK", "GHBBWFK", "HHBBWFK", "IHBBWFK", "GIBBWFK", "HIBBWFK", "IIBBWFK", "GGCBWFK", "HGCBWFK", "IGCBWFK", "GHCBWFK", "HHCBWFK", "IHCBWFK", "GICBWFK", "HICBWFK", "IICBWFK", "GGACWFK", "HGACWFK", "IGACWFK", "GHACWFK", "HHACWFK", "IHACWFK", "GIACWFK", "HIACWFK", "IIACWFK", "GGBCWFK", "HGBCWFK", "IGBCWFK", "GHBCWFK", "HHBCWFK", "IHBCWFK", "GIBCWFK", "HIBCWFK", "IIBCWFK", "GGCCWFK", "HGCCWFK", "IGCCWFK", "GHCCWFK", "HHCCWFK", "IHCCWFK", "GICCWFK", "HICCWFK", "IICCWFK", "GGAAXFK", "HGAAXFK", "IGAAXFK", "GHAAXFK", "HHAAXFK", "IHAAXFK", "GIAAXFK", "HIAAXFK", "IIAAXFK", "GGBAXFK", "HGBAXFK", "IGBAXFK", "GHBAXFK", "HHBAXFK", "IHBAXFK", "GIBAXFK", "HIBAXFK", "IIBAXFK", "GGCAXFK", "HGCAXFK", "IGCAXFK", "GHCAXFK", "HHCAXFK", "IHCAXFK", "GICAXFK", "HICAXFK", "IICAXFK", "GGABXFK", "HGABXFK", "IGABXFK", "GHABXFK", "HHABXFK", "IHABXFK", "GIABXFK", "HIABXFK", "IIABXFK", "GGBBXFK", "HGBBXFK", "IGBBXFK", "GHBBXFK", "HHBBXFK", "IHBBXFK", "GIBBXFK", "HIBBXFK", "IIBBXFK", "GGCBXFK", "HGCBXFK", "IGCBXFK", "GHCBXFK", "HHCBXFK", "IHCBXFK", "GICBXFK", "HICBXFK", "IICBXFK", "GGACXFK", "HGACXFK", "IGACXFK", "GHACXFK", "HHACXFK", "IHACXFK", "GIACXFK", "HIACXFK", "IIACXFK", "GGBCXFK", "HGBCXFK", "IGBCXFK", "GHBCXFK", "HHBCXFK", "IHBCXFK", "GIBCXFK", "HIBCXFK", "IIBCXFK", "GGCCXFK", "HGCCXFK", "IGCCXFK", "GHCCXFK", "HHCCXFK", "IHCCXFK", "GICCXFK", "HICCXFK", "IICCXFK", "GGAAYFK", "HGAAYFK", "IGAAYFK", "GHAAYFK", "HHAAYFK", "IHAAYFK", "GIAAYFK", "HIAAYFK", "IIAAYFK", "GGBAYFK", "HGBAYFK", "IGBAYFK", "GHBAYFK", "HHBAYFK", "IHBAYFK", "GIBAYFK", "HIBAYFK", "IIBAYFK", "GGCAYFK", "HGCAYFK", "IGCAYFK", "GHCAYFK", "HHCAYFK", "IHCAYFK", "GICAYFK", "HICAYFK", "IICAYFK", "GGABYFK", "HGABYFK", "IGABYFK", "GHABYFK", "HHABYFK", "IHABYFK", "GIABYFK", "HIABYFK", "IIABYFK", "GGBBYFK", "HGBBYFK", "IGBBYFK", "GHBBYFK", "HHBBYFK", "IHBBYFK", "GIBBYFK", "HIBBYFK", "IIBBYFK", "GGCBYFK", "HGCBYFK", "IGCBYFK", "GHCBYFK", "HHCBYFK", "IHCBYFK", "GICBYFK", "HICBYFK", "IICBYFK", "GGACYFK", "HGACYFK", "IGACYFK", "GHACYFK", "HHACYFK", "IHACYFK", "GIACYFK", "HIACYFK", "IIACYFK", "GGBCYFK", "HGBCYFK", "IGBCYFK", "GHBCYFK", "HHBCYFK", "IHBCYFK", "GIBCYFK", "HIBCYFK", "IIBCYFK", "GGCCYFK", "HGCCYFK", "IGCCYFK", "GHCCYFK", "HHCCYFK", "IHCCYFK", "GICCYFK", "HICCYFK", "IICCYFK", "GGAAWDL", "HGAAWDL", "IGAAWDL", "GHAAWDL", "HHAAWDL", "IHAAWDL", "GIAAWDL", "HIAAWDL", "IIAAWDL", "GGBAWDL", "HGBAWDL", "IGBAWDL", "GHBAWDL", "HHBAWDL", "IHBAWDL", "GIBAWDL", "HIBAWDL", "IIBAWDL", "GGCAWDL", "HGCAWDL", "IGCAWDL", "GHCAWDL", "HHCAWDL", "IHCAWDL", "GICAWDL", "HICAWDL", "IICAWDL", "GGABWDL", "HGABWDL", "IGABWDL", "GHABWDL", "HHABWDL", "IHABWDL", "GIABWDL", "HIABWDL", "IIABWDL", "GGBBWDL", "HGBBWDL", "IGBBWDL", "GHBBWDL", "HHBBWDL", "IHBBWDL", "GIBBWDL", "HIBBWDL", "IIBBWDL", "GGCBWDL", "HGCBWDL", "IGCBWDL", "GHCBWDL", "HHCBWDL", "IHCBWDL", "GICBWDL", "HICBWDL", "IICBWDL", "GGACWDL", "HGACWDL", "IGACWDL", "GHACWDL", "HHACWDL", "IHACWDL", "GIACWDL", "HIACWDL", "IIACWDL", "GGBCWDL", "HGBCWDL", "IGBCWDL", "GHBCWDL", "HHBCWDL", "IHBCWDL", "GIBCWDL", "HIBCWDL", "IIBCWDL", "GGCCWDL", "HGCCWDL", "IGCCWDL", "GHCCWDL", "HHCCWDL", "IHCCWDL", "GICCWDL", "HICCWDL", "IICCWDL", "GGAAXDL", "HGAAXDL", "IGAAXDL", "GHAAXDL", "HHAAXDL", "IHAAXDL", "GIAAXDL", "HIAAXDL", "IIAAXDL", "GGBAXDL", "HGBAXDL", "IGBAXDL", "GHBAXDL", "HHBAXDL", "IHBAXDL", "GIBAXDL", "HIBAXDL", "IIBAXDL", "GGCAXDL", "HGCAXDL", "IGCAXDL", "GHCAXDL", "HHCAXDL", "IHCAXDL", "GICAXDL", "HICAXDL", "IICAXDL", "GGABXDL", "HGABXDL", "IGABXDL", "GHABXDL", "HHABXDL", "IHABXDL", "GIABXDL", "HIABXDL", "IIABXDL", "GGBBXDL", "HGBBXDL", "IGBBXDL", "GHBBXDL", "HHBBXDL", "IHBBXDL", "GIBBXDL", "HIBBXDL", "IIBBXDL", "GGCBXDL", "HGCBXDL", "IGCBXDL", "GHCBXDL", "HHCBXDL", "IHCBXDL", "GICBXDL", "HICBXDL", "IICBXDL", "GGACXDL", "HGACXDL", "IGACXDL", "GHACXDL", "HHACXDL", "IHACXDL", "GIACXDL", "HIACXDL", "IIACXDL", "GGBCXDL", "HGBCXDL", "IGBCXDL", "GHBCXDL", "HHBCXDL", "IHBCXDL", "GIBCXDL", "HIBCXDL", "IIBCXDL", "GGCCXDL", "HGCCXDL", "IGCCXDL", "GHCCXDL", "HHCCXDL", "IHCCXDL", "GICCXDL", "HICCXDL", "IICCXDL", "GGAAYDL", "HGAAYDL", "IGAAYDL", "GHAAYDL", "HHAAYDL", "IHAAYDL", "GIAAYDL", "HIAAYDL", "IIAAYDL", "GGBAYDL", "HGBAYDL", "IGBAYDL", "GHBAYDL", "HHBAYDL", "IHBAYDL", "GIBAYDL", "HIBAYDL", "IIBAYDL", "GGCAYDL", "HGCAYDL", "IGCAYDL", "GHCAYDL", "HHCAYDL", "IHCAYDL", "GICAYDL", "HICAYDL", "IICAYDL", "GGABYDL", "HGABYDL", "IGABYDL", "GHABYDL", "HHABYDL", "IHABYDL", "GIABYDL", "HIABYDL", "IIABYDL", "GGBBYDL", "HGBBYDL", "IGBBYDL", "GHBBYDL", "HHBBYDL", "IHBBYDL", "GIBBYDL", "HIBBYDL", "IIBBYDL", "GGCBYDL", "HGCBYDL", "IGCBYDL", "GHCBYDL", "HHCBYDL", "IHCBYDL", "GICBYDL", "HICBYDL", "IICBYDL", "GGACYDL", "HGACYDL", "IGACYDL", "GHACYDL", "HHACYDL", "IHACYDL", "GIACYDL", "HIACYDL", "IIACYDL", "GGBCYDL", "HGBCYDL", "IGBCYDL", "GHBCYDL", "HHBCYDL", "IHBCYDL", "GIBCYDL", "HIBCYDL", "IIBCYDL", "GGCCYDL", "HGCCYDL", "IGCCYDL", "GHCCYDL", "HHCCYDL", "IHCCYDL", "GICCYDL", "HICCYDL", "IICCYDL", "GGAAWEL", "HGAAWEL", "IGAAWEL", "GHAAWEL", "HHAAWEL", "IHAAWEL", "GIAAWEL", "HIAAWEL", "IIAAWEL", "GGBAWEL", "HGBAWEL", "IGBAWEL", "GHBAWEL", "HHBAWEL", "IHBAWEL", "GIBAWEL", "HIBAWEL", "IIBAWEL", "GGCAWEL", "HGCAWEL", "IGCAWEL", "GHCAWEL", "HHCAWEL", "IHCAWEL", "GICAWEL", "HICAWEL", "IICAWEL", "GGABWEL", "HGABWEL", "IGABWEL", "GHABWEL", "HHABWEL", "IHABWEL", "GIABWEL", "HIABWEL", "IIABWEL", "GGBBWEL", "HGBBWEL", "IGBBWEL", "GHBBWEL", "HHBBWEL", "IHBBWEL", "GIBBWEL", "HIBBWEL", "IIBBWEL", "GGCBWEL", "HGCBWEL", "IGCBWEL", "GHCBWEL", "HHCBWEL", "IHCBWEL", "GICBWEL", "HICBWEL", "IICBWEL", "GGACWEL", "HGACWEL", "IGACWEL", "GHACWEL", "HHACWEL", "IHACWEL", "GIACWEL", "HIACWEL", "IIACWEL", "GGBCWEL", "HGBCWEL", "IGBCWEL", "GHBCWEL", "HHBCWEL", "IHBCWEL", "GIBCWEL", "HIBCWEL", "IIBCWEL", "GGCCWEL", "HGCCWEL", "IGCCWEL", "GHCCWEL", "HHCCWEL", "IHCCWEL", "GICCWEL", "HICCWEL", "IICCWEL", "GGAAXEL", "HGAAXEL", "IGAAXEL", "GHAAXEL", "HHAAXEL", "IHAAXEL", "GIAAXEL", "HIAAXEL", "IIAAXEL", "GGBAXEL", "HGBAXEL", "IGBAXEL", "GHBAXEL", "HHBAXEL", "IHBAXEL", "GIBAXEL", "HIBAXEL", "IIBAXEL", "GGCAXEL", "HGCAXEL", "IGCAXEL", "GHCAXEL", "HHCAXEL", "IHCAXEL", "GICAXEL", "HICAXEL", "IICAXEL", "GGABXEL", "HGABXEL", "IGABXEL", "GHABXEL", "HHABXEL", "IHABXEL", "GIABXEL", "HIABXEL", "IIABXEL", "GGBBXEL", "HGBBXEL", "IGBBXEL", "GHBBXEL", "HHBBXEL", "IHBBXEL", "GIBBXEL", "HIBBXEL", "IIBBXEL", "GGCBXEL", "HGCBXEL", "IGCBXEL", "GHCBXEL", "HHCBXEL", "IHCBXEL", "GICBXEL", "HICBXEL", "IICBXEL", "GGACXEL", "HGACXEL", "IGACXEL", "GHACXEL", "HHACXEL", "IHACXEL", "GIACXEL", "HIACXEL", "IIACXEL", "GGBCXEL", "HGBCXEL", "IGBCXEL", "GHBCXEL", "HHBCXEL", "IHBCXEL", "GIBCXEL", "HIBCXEL", "IIBCXEL", "GGCCXEL", "HGCCXEL", "IGCCXEL", "GHCCXEL", "HHCCXEL", "IHCCXEL", "GICCXEL", "HICCXEL", "IICCXEL", "GGAAYEL", "HGAAYEL", "IGAAYEL", "GHAAYEL", "HHAAYEL", "IHAAYEL", "GIAAYEL", "HIAAYEL", "IIAAYEL", "GGBAYEL", "HGBAYEL", "IGBAYEL", "GHBAYEL", "HHBAYEL", "IHBAYEL", "GIBAYEL", "HIBAYEL", "IIBAYEL", "GGCAYEL", "HGCAYEL", "IGCAYEL", "GHCAYEL", "HHCAYEL", "IHCAYEL", "GICAYEL", "HICAYEL", "IICAYEL", "GGABYEL", "HGABYEL", "IGABYEL", "GHABYEL", "HHABYEL", "IHABYEL", "GIABYEL", "HIABYEL", "IIABYEL", "GGBBYEL", "HGBBYEL", "IGBBYEL", "GHBBYEL", "HHBBYEL", "IHBBYEL", "GIBBYEL", "HIBBYEL", "IIBBYEL", "GGCBYEL", "HGCBYEL", "IGCBYEL", "GHCBYEL", "HHCBYEL", "IHCBYEL", "GICBYEL", "HICBYEL", "IICBYEL", "GGACYEL", "HGACYEL", "IGACYEL", "GHACYEL", "HHACYEL", "IHACYEL", "GIACYEL", "HIACYEL", "IIACYEL", "GGBCYEL", "HGBCYEL", "IGBCYEL", "GHBCYEL", "HHBCYEL", "IHBCYEL", "GIBCYEL", "HIBCYEL", "IIBCYEL", "GGCCYEL", "HGCCYEL", "IGCCYEL", "GHCCYEL", "HHCCYEL", "IHCCYEL", "GICCYEL", "HICCYEL", "IICCYEL", "GGAAWFL", "HGAAWFL", "IGAAWFL", "GHAAWFL", "HHAAWFL", "IHAAWFL", "GIAAWFL", "HIAAWFL", "IIAAWFL", "GGBAWFL", "HGBAWFL", "IGBAWFL", "GHBAWFL", "HHBAWFL", "IHBAWFL", "GIBAWFL", "HIBAWFL", "IIBAWFL", "GGCAWFL", "HGCAWFL", "IGCAWFL", "GHCAWFL", "HHCAWFL", "IHCAWFL", "GICAWFL", "HICAWFL", "IICAWFL", "GGABWFL", "HGABWFL", "IGABWFL", "GHABWFL", "HHABWFL", "IHABWFL", "GIABWFL", "HIABWFL", "IIABWFL", "GGBBWFL", "HGBBWFL", "IGBBWFL", "GHBBWFL", "HHBBWFL", "IHBBWFL", "GIBBWFL", "HIBBWFL", "IIBBWFL", "GGCBWFL", "HGCBWFL", "IGCBWFL", "GHCBWFL", "HHCBWFL", "IHCBWFL", "GICBWFL", "HICBWFL", "IICBWFL", "GGACWFL", "HGACWFL", "IGACWFL", "GHACWFL", "HHACWFL", "IHACWFL", "GIACWFL", "HIACWFL", "IIACWFL", "GGBCWFL", "HGBCWFL", "IGBCWFL", "GHBCWFL", "HHBCWFL", "IHBCWFL", "GIBCWFL", "HIBCWFL", "IIBCWFL", "GGCCWFL", "HGCCWFL", "IGCCWFL", "GHCCWFL", "HHCCWFL", "IHCCWFL", "GICCWFL", "HICCWFL", "IICCWFL", "GGAAXFL", "HGAAXFL", "IGAAXFL", "GHAAXFL", "HHAAXFL", "IHAAXFL", "GIAAXFL", "HIAAXFL", "IIAAXFL", "GGBAXFL", "HGBAXFL", "IGBAXFL", "GHBAXFL", "HHBAXFL", "IHBAXFL", "GIBAXFL", "HIBAXFL", "IIBAXFL", "GGCAXFL", "HGCAXFL", "IGCAXFL", "GHCAXFL", "HHCAXFL", "IHCAXFL", "GICAXFL", "HICAXFL", "IICAXFL", "GGABXFL", "HGABXFL", "IGABXFL", "GHABXFL", "HHABXFL", "IHABXFL", "GIABXFL", "HIABXFL", "IIABXFL", "GGBBXFL", "HGBBXFL", "IGBBXFL", "GHBBXFL", "HHBBXFL", "IHBBXFL", "GIBBXFL", "HIBBXFL", "IIBBXFL", "GGCBXFL", "HGCBXFL", "IGCBXFL", "GHCBXFL", "HHCBXFL", "IHCBXFL", "GICBXFL", "HICBXFL", "IICBXFL", "GGACXFL", "HGACXFL", "IGACXFL", "GHACXFL", "HHACXFL", "IHACXFL", "GIACXFL", "HIACXFL", "IIACXFL", "GGBCXFL", "HGBCXFL", "IGBCXFL", "GHBCXFL", "HHBCXFL", "IHBCXFL", "GIBCXFL", "HIBCXFL", "IIBCXFL", "GGCCXFL", "HGCCXFL", "IGCCXFL", "GHCCXFL", "HHCCXFL", "IHCCXFL", "GICCXFL", "HICCXFL", "IICCXFL", "GGAAYFL", "HGAAYFL", "IGAAYFL", "GHAAYFL", "HHAAYFL", "IHAAYFL", "GIAAYFL", "HIAAYFL", "IIAAYFL", "GGBAYFL", "HGBAYFL", "IGBAYFL", "GHBAYFL", "HHBAYFL", "IHBAYFL", "GIBAYFL", "HIBAYFL", "IIBAYFL", "GGCAYFL", "HGCAYFL", "IGCAYFL", "GHCAYFL", "HHCAYFL", "IHCAYFL", "GICAYFL", "HICAYFL", "IICAYFL", "GGABYFL", "HGABYFL", "IGABYFL", "GHABYFL", "HHABYFL", "IHABYFL", "GIABYFL", "HIABYFL", "IIABYFL", "GGBBYFL", "HGBBYFL", "IGBBYFL", "GHBBYFL", "HHBBYFL", "IHBBYFL", "GIBBYFL", "HIBBYFL", "IIBBYFL", "GGCBYFL", "HGCBYFL", "IGCBYFL", "GHCBYFL", "HHCBYFL", "IHCBYFL", "GICBYFL", "HICBYFL", "IICBYFL", "GGACYFL", "HGACYFL", "IGACYFL", "GHACYFL", "HHACYFL", "IHACYFL", "GIACYFL", "HIACYFL", "IIACYFL", "GGBCYFL", "HGBCYFL", "IGBCYFL", "GHBCYFL", "HHBCYFL", "IHBCYFL", "GIBCYFL", "HIBCYFL", "IIBCYFL", "GGCCYFL", "HGCCYFL", "IGCCYFL", "GHCCYFL", "HHCCYFL", "IHCCYFL", "GICCYFL", "HICCYFL", "IICCYFL"
		};
		String[] result = instance.getWords();
		// One test is enough
		assertEquals("array lengths equal", expResult.length, result.length);
		for (int i = 0; i < expResult.length; i++)
		{
			assertEquals("array elements equal", expResult[i], result[i]);
		}
		System.out.println("getWords finished");
	}

	public static Test suite()
	{
		return new TestSuite(PhoneNumberTest.class);
	}

	public static void main(String[] args)
	{
		if (args.length == 0)
		{
			System.err.println("Usage: <7-digit-telephone-number> <output-file-path>");
			System.exit(0);
		}

		if (args[0].equals("test"))
		{
			String[] test =
			{
				PhoneNumberTest.class.getName()
			};
			junit.textui.TestRunner.main(test);
		}
		else
		{
			String number = args[0];

			if (args[1] == null)
			{
				System.err.println("Usage: <7-digit-telephone-number> <output-file-path>");
				System.exit(0);
			}

			File output = new File(args[1]);

			// Does the file already exist
			if (!output.exists())
			{
				try
				{
					// Try creating the file
					output.createNewFile();
				}
				catch (IOException e)
				{
					System.err.println("There was an error creating that file: " + e.getMessage());
					System.exit(0);
				}
			}

			PrintStream stream = null;

			try
			{
				stream = new PrintStream(output);
			}
			catch (FileNotFoundException e)
			{
				System.err.println("There was an error writing to your file: " + e.getMessage());
				System.exit(0);
			}

			PhoneNumber phoneNumber = null;

			try
			{
				phoneNumber = new PhoneNumber(number);
			}
			catch (IllegalArgumentException e)
			{
				System.err.println(e.getMessage());
				System.exit(0);
			}

			String[] words = phoneNumber.getWords();
			for (int i = 0; i < words.length; i++)
			{
				stream.println(words[i]);
			}
			System.exit(1);
		}
	}
}
