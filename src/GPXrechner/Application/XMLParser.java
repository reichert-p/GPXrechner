package GPXrechner.Application;

import GPXrechner.Plugin.Parsing.GPXReader.NoDataException;

public interface XMLParser {
    void read(String path) throws NoDataException;
}
