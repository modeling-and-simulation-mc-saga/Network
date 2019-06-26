package analysisSamples;

import networkModels.*;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import myLib.utils.FileIO;
import network.*;

public class BADegreeDistribution {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int n = 1000000;//Number of nodes
        int m = 1;
        AbstractNetwork sys = new BA(n, m);
        sys.createNetwork();
        analysis.DegreeDistribution dd = new analysis.DegreeDistribution(sys);
        List<Point2D.Double> pList = dd.getDistribution();
        try (BufferedWriter out = FileIO.openWriter("BA-DegreeDistribution.txt")) {
            for (Point2D.Double p : pList) {
                FileIO.writeSSV(out, p.x, p.y);
            }
        }
        
        try(BufferedWriter out=FileIO.openWriter("accumulatedDegreeDistribution.txt")){
            double y=0.;
            for (Point2D.Double p : pList) {
                y += p.y;
                FileIO.writeSSV(out, p.x, y);
            }
        }
    }

}
