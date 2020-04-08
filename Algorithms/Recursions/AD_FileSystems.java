package data_structure.ac_recursion;

import java.io.File;

public class AD_FileSystems {

    public static void main(String[] args) {
        String path = "/home/chobanyan/Dropbox/AUA/CS121_DataStructures/";
        long pathSize = diskUsage(new File(path));
    }


    /**
     * Calculates the total disk usage (in bytes) of the portion of the file system rooted
     * at the given path, while printing a summary akin to the standard 'du' Unix tool.
     * @param root new File(String pathName)
     * @return The cumulative disk space of the current location
     */
    private static long diskUsage(File root) {
        // Immediate disk space used by the entry
        long total = root.length();

        // If this is a directory,
        if (root.isDirectory()) {
            // Then for each child
            for (String childName: root.list()){
                // Compose full path to child
                File child = new File(root, childName);
                // Add child’s usage to total
                total += diskUsage(child);
            }
        }
        System.out.println(total + "\t" + root);
        return total;
    }
}
