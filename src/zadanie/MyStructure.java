package zadanie;

import java.util.List;
//W ogólnym zarysie relacji klas dostrzegłem duże podobieństwo do strukturalnego wzorca Composite Pattern, z jedną
// różnicą -> nie znalazłem metod pozwalających dodawać oraz usuwać nowe elementy do kompozytu.
//Uznałem że moim zadaniem jest stworzenie struktury tak aby mogła być jednocześnie traktowana
//jako pojedynczy Node oraz jako wiele z nich, w związku z tym zdecydowałem się zaimplementować interfejs ICompositeNode.
// Najlepszy sposób w jaki kompozycja obiektów mogłaby udawać pojedynczy obiekt w kontekście zwracania kodu i renderera
// wydał mi się być zwróceniem połączonych kodów wszystkich posiadanych node -> stąd zastosowanie StringBuildera.
// Aby metody wyszukujące były gotowe na obsługę innych kompozytów zastosowałem wyszukiwanie po metodzie contains
// ponieważ poszukiwany fragment mógłby być jednym z wielu w innym kompozycie. Niestety rozwiązanie to ma taką wadę,
// że we wcześniej wymienionej sytuacji nie jesteśmy w stanie zwrócić konkretnego Node a tylko kompozyt który zawiera
// jego kod. Dlatego, że nie jesteśmy w stanie z poziomu MyStructure rozróżnić czy stream przegląda właśnie kompozyt
// czy Node a nawet jeśli aby mieć dostęp do metod find kompozytu trzeba by było zastosować castowanie.

public class MyStructure implements IMyStructure, ICompositeNode {
    private List<INode> nodes;

    @Override
    public INode findByCode(String code) {


        return nodes.stream()
                .filter(node -> code.contains(node.getCode()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public INode findByRenderer(String renderer) {
        return nodes.stream()
                .filter(node -> renderer.contains(node.getRenderer()))
                .findAny()
                .orElse(null);
    }

    @Override
    public int count() {
        return nodes.size();
    }

    @Override
    public String getCode() {
        StringBuilder combinedCode = new StringBuilder();
        for (INode node : nodes) {
            combinedCode.append(node.getCode());
        }
        return combinedCode.toString();
    }

    @Override
    public String getRenderer() {
        StringBuilder combinedRenderer = new StringBuilder();
        for (INode node : nodes) {
            combinedRenderer.append(node.getRenderer());
        }
        return combinedRenderer.toString();
    }

    @Override
    public List<INode> getNodes() {
        return nodes;
    }
}
