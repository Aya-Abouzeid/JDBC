package eg.edu.alexu.csd.oop.VectorDrawing;

import java.awt.Color;
import java.awt.Stroke;
import java.io.File;
	import java.lang.reflect.Constructor;
	import java.net.URL;
	import java.net.URLClassLoader;

	import javax.swing.JOptionPane;
/**
 *
 * @author aya_a_000
 *
 */
public class LoaderOfClasses {

/**.
 *constructor
 */
    private static Constructor<?>[] curConst;
    /**.
    *file
    */
    static File file;
    /**.
    *url
    */
    static URL url;
    /**.
    *url[]
    */
    static URL[] urls;
    /**.
    *boolean class loaded
    */
    public static boolean classLoaded = false;
    /**.
    *gui object
    */
    static GUI gui = new GUI();
    /**.
    *boolean
    *@param classPath path
    *@return boolean
    */
    public static boolean load(String classPath) {
         file = new File(classPath);


        try {
            url = file.toURI().toURL();
             urls = new URL[] {url};
            @SuppressWarnings("resource")
            ClassLoader classLoader = new URLClassLoader(urls);
Class<?> clas =
classLoader.loadClass("eg.edu.alexu.csd.oop.VectorDrawing.Square");
            curConst = clas.getConstructors();
             classLoaded = true;
             gui.clasLoaded(classLoaded);
            return true;
        } catch (Exception e) {
        	System.out.println(e);
            JOptionPane.showMessageDialog(null, "Please Select Valid Class");
            return false;
        }
    }

    public Shape getObject(int x1, int y1, int x2, int y2,int extra1,
    		int extra2, Color color, Color color2, Stroke s) {
        try {
            Object obj = curConst[1].newInstance(x1, y1, x2, y2, extra1,
    	    		 extra2,  color, color2, s);
            return (Shape) obj;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Select Valid Class");
        }
        return null;
    }

    public boolean isSquare(Object obj) {
        return obj.getClass().getSimpleName().equals(curConst[1].getClass()
                .getSimpleName());
    }

}
