import java.util.*;

public class Diary {
    private List<DiaryEntry> diaryEntries = new ArrayList<>();
    //           id        keyword
    private Map<Integer, Set<String>> searchMap = new HashMap<>();

    private static String DATA_PATH = "data/";

	public Diary() {
    	loadEntries();
	}
	
    public void createEntry() {
        // Practice 1 - Create a diary entry
        // Practice 2 - Store the created entry in a file
        String title = DiaryUI.input("title: ");
        String content = DiaryUI.input("content: ");
        DiaryEntry entry = new DiaryEntry(title, content);

        diaryEntries.add(entry);
        addSearchKeywords(entry);
        saveEntry(entry);

        DiaryUI.print("The entry is saved.");
    }

    public void listEntries() {
        // Practice 1 - List all the entries - sorted in created time by descending order
        // Practice 2 - Your list should contain previously stored files
        for(int i = diaryEntries.size()-1; i>=0; i--){
            DiaryEntry entry = diaryEntries.get(i);
            DiaryUI.print(entry.getShortString());
        }
    }

    public void readEntry(int id) {
        // Practice 1 - Read the entry of given id
        // Practice 2 - Your read should contain previously stored files
        DiaryEntry entry = findEntry(id);
        if (entry == null){
            DiaryUI.print("There is no entry with id " + id + ".");
            return;
        }
        DiaryUI.print(entry.getFullString());
    }

    public void deleteEntry(int id) {
        // Practice 1 - Delete the entry of given id
        // Practice 2 - Delete the file of the entry
        DiaryEntry entry = findEntry(id);
        if (entry == null) {
            DiaryUI.print("There is no entry with id "+id+ ".");
            return;
        }

        if(StorageManager.deleteFile(DATA_PATH + entry.getFileName())) {
            diaryEntries.remove(entry);
            searchMap.remove(id);

            DiaryUI.print("Entry " + id + " is removed.");
        }
    }

    public void searchEntry(String keyword) {
        // Practice 1 - Search and print all the entries containing given keyword
        // Practice 2 - Your search should contain previously stored files
        List<DiaryEntry> searchResult = new ArrayList<>();

        for (int id : searchMap.keySet()){
            Set<String> keywords = searchMap.get(id);
            if (keywords.contains(keyword)) {
                searchResult.add(findEntry(id));
            }
        }

        if (searchResult.isEmpty()){
            DiaryUI.print("There is no entry that contains \"" + keyword + "\".");
            return;
        }

        for(DiaryEntry entry : searchResult){
            DiaryUI.print(entry.getFullString());
            DiaryUI.print("");
        }
    }

    private DiaryEntry findEntry(int id) {
	    for (DiaryEntry entry : diaryEntries){
	        if(entry.getID() == id){
	            return entry;
            }
        }
	    return null;
    }

    private void addSearchKeywords(DiaryEntry entry){
	    Set<String> keywords = new HashSet<>();

	    for (String keyword : entry.getTitle().split("\\s")) {
	        keywords.add(keyword);
        }

        for (String keyword : entry.getContent().split("\\s")) {
            keywords.add(keyword);
        }

        searchMap.put(entry.getID(),keywords);
    }

    private void saveEntry(DiaryEntry entry){
	    String fileName = DATA_PATH + entry.getFileName();
	    List<String> fileData = entry.getFileData();
	    StorageManager.writeLines(fileName, fileData);
    }

    private void loadEntries() {
	    List<List<String>> entryDataList = StorageManager.directoryChildrenLines(DATA_PATH);
	    for (List<String> entryData : entryDataList){
	        if(entryData.size() < 4 ){
	            continue;
            }
	        int id = Integer.parseInt(entryData.get(0));
	        String createdTime = entryData.get(1);
	        String title = entryData.get(2);
	        String content = entryData.get(3);

	        DiaryEntry entry = new DiaryEntry(id, title, content, createdTime);
	        diaryEntries.add(entry);
	        addSearchKeywords(entry);

	        DiaryEntry.updateIncrementId(id);
        }
    }

}
