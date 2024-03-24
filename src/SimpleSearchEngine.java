import java.util.*;

public class SimpleSearchEngine {
    private Map<String, Set<String>> invertedIndex;

    public SimpleSearchEngine() {
        invertedIndex = new HashMap<>();
    }

    // Index a document
    public void indexDocument(String documentId, String[] words) {
        for (String word : words) {
            invertedIndex.computeIfAbsent(word, k -> new HashSet<>()).add(documentId);
        }
    }

    // Search for documents containing all query terms
    public Set<String> search(String[] queryTerms) {
        Set<String> result = new HashSet<>();
        boolean firstTerm = true;

        for (String term : queryTerms) {
            if (invertedIndex.containsKey(term)) {
                if (firstTerm) {
                    result.addAll(invertedIndex.get(term));
                    firstTerm = false;
                } else {
                    result.retainAll(invertedIndex.get(term));
                }
            } else {
                return new HashSet<>(); // Return empty set if any query term is not found
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SimpleSearchEngine searchEngine = new SimpleSearchEngine();

        // Index some documents
        searchEngine.indexDocument("doc1", new String[]{"java", "programming", "language"});
        searchEngine.indexDocument("doc2", new String[]{"python", "programming", "language"});
        searchEngine.indexDocument("doc3", new String[]{"java", "development", "tools"});

        // Perform a search
        String[] query = {"java", "programming"};
        Set<String> results = searchEngine.search(query);

        // Display search results
        if (results.isEmpty()) {
            System.out.println("No documents found matching the query.");
        } else {
            System.out.println("Documents found matching the query:");
            for (String docId : results) {
                System.out.println(docId);
            }
        }
    }
}
