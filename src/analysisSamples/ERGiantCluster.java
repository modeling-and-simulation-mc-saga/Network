package analysisSamples;

import java.io.BufferedWriter;
import java.io.IOException;
import myLib.utils.FileIO;
import networkModels.ER;

/**
 *
 * @author tadaki
 */
public class ERGiantCluster {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int n = 1000;
        int numStep = 200;
        try (BufferedWriter out = FileIO.openWriter("GiantClusterSize.txt")) {
            for (int i = 1; i < numStep; i++) {
                double p = (4. / n) * i / numStep;
                int m = (int) (p * n * (n - 1) / 2);
                ER er = new ER(n, m);
                er.createNetwork();
                analysis.GiantCluster gc = new analysis.GiantCluster(er);
                int k = gc.findClusters();
                int maxSize = gc.findLargestClusterSize();
                FileIO.writeSSV(out, p, maxSize, k, m);
            }
        }
    }

}
