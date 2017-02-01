var giocatori = new Bloodhound({
    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('giocatore'),
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    prefetch: {
        url: 'javascript/giocatori.json',
    }
});

giocatori.clearPrefetchCache();
giocatori.initialize();

$('#prefetch .typeahead').typeahead(null, {
    name: 'giocatori',
    displayKey: 'giocatore',
    source: giocatori.ttAdapter(),
}); 