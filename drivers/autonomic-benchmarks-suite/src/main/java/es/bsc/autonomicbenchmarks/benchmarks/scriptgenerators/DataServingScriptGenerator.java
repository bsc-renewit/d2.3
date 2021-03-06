package es.bsc.autonomicbenchmarks.benchmarks.scriptgenerators;


public class DataServingScriptGenerator {

    private static final String END_OF_LINE = System.getProperty("line.separator");
    private static final String YCSB_PATH = "/home/ubuntu/YCSB";
    private static final String CASSANDRA_PATH = "/home/ubuntu/apache-cassandra-0.7.3";
    private static final String RUN_CONFIG_PATH = "/home/ubuntu/YCSB/settings.dat";

    public String generateScript(int cpus) {
        return "#cloud-config" + END_OF_LINE
                + "password: bsc" + END_OF_LINE
                + "chpasswd: { expire: False }" + END_OF_LINE
                + "ssh_pwauth: True" + END_OF_LINE
                + "runcmd:" + END_OF_LINE
                + " - [ cd, " + CASSANDRA_PATH + " ]" + END_OF_LINE
                + generateStartCassandraCommand() + END_OF_LINE
                + " - [ cd, " + YCSB_PATH + " ]" + END_OF_LINE
                + generatedModifyThreadsCommand(cpus) + END_OF_LINE
                + generatePrintTimestampStartCommand() + END_OF_LINE
                + generateRunCommand() + END_OF_LINE
                + END_OF_LINE;
    }
    
    private String generateStartCassandraCommand() {
        return " - [ bin/cassandra ]";
    }
    
    private String generatedModifyThreadsCommand(int cpus) {
        return " - sed -i.bak -e '2d' " + RUN_CONFIG_PATH + " " + END_OF_LINE
                + " - sed -i.bak '1 a\\threadcount=" + cpus + "' " + RUN_CONFIG_PATH;
    }
    
    private String generateRunCommand() {
        return " - [ /usr/bin/time, " + " -f, " + "\"real_time %e\","
                + " ./run.command,"
                + " \"&> results.txt\" ]";
    }

    private String generatePrintTimestampStartCommand() {
        return " - echo \"timestamp_start:$(date +%s)\"";
    }
    
}
