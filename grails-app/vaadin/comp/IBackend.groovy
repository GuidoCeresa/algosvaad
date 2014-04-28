package comp
/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 26-1-14
 * Time: 17:57
 */
public interface IBackend {
    List getRecords()

    boolean storeRecord(def record)

    boolean deleteRecord(def record)
} //fine dell'interfaccia
