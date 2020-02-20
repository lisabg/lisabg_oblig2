# lisabg_oblig2 - Assigment:

### Introduksjon
I denne oppgaven skal du lage en applikasjon i Android Studio. Du vil få erfaring med
JSON som dataformat, og bruk av avanserte biblioteker som brukes i Android-utvikling,
samt hvordan utføre API-kall og deserialisere respons fra API til objekter.
Kotlin skal benyttes som programmeringsspråk, og XML til design. Denne oppgaven
skal løses individuelt, og for å få den godkjent må alle deloppgaver være gjort og appen
skal kjøre med Android 6.0 (API 23) uten å krasje.

### Oppgaven
Oppgaven er tredelt, og bør gjøres i gitt rekkefølge:
1. Hente informasjon om alpakkaer fra et API og lagre dette som objekter i en liste.
2. Opprette et RecyclerView som skal vise informasjon om alpakkaer ved å populere
det med objekter fra lista (fra punkt 1).
3. Vise en laste-animasjon.
**Merk:** Deloppgave 1 og 2 tar betydelig lenger tid enn deloppgave 3.

### Formelle krav:
Disse kravene gjelder for alle deloppgaver.

1. Appens layout skal støtte ulike skjermstørrelser, slik at den skal se lik ut på alle
skjermer. Det betyr at den potensielt skal kunne kjøres på forskjellige emulatorer
med ulik skjermstørrelse og oppløsning.
2. Alle filer bør ha minimalt med Warnings.

### Komme i gang
Opprett et nytt Android Studio-prosjekt, sett prosjektnavn til:
[ditt uio-brukernavn]_oblig2
Videre velger du “Empty Activity” og “Kotlin” som programmeringsspråk.
Velg API 23 som target SDK/Minimum API Level.

### Relevante biblioteker
#### Generelt:
- Om skjermstørrelse: https://developer.android.com/training/multiscreen/screensizes
#### Del 1:
- Gson: https://github.com/google/gson/blob/master/UserGuide.md
- Coroutines: https://developer.android.com/kotlin/coroutines
#### Del 2:
- RecyclerView: https://developer.android.com/guide/topics/ui/layout/recyclerview
- CardView: https://developer.android.com/guide/topics/ui/layout/cardview
- CircularImageView: https://github.com/hdodenhof/CircleImageView
- Glide: https://github.com/bumptech/glide
- Coroutines (se link på del 1)
#### Del 3:
- ProgressBar: https://developer.android.com/reference/android/widget/ProgressBar

## Del 1: Hente data fra internett
I denne delen skal du kort forklart:
- Hente ut data fra et endepunkt (API).
- Parse responsen til objekter.
- Legge objektene i en liste.

#### Om API-et
API-et inneholder et JSON-array som kan inneholde ingen eller flere JSON-objekter.
Hvert JSON-objekt representerer en alpakka. Dette er egenskapene til en alpakka og
tilhørende type i JSON:

```javascript
[
  {
    “name”: “string”,     // Alpakkaens navn
    “location”: “string”, // Stedet alpakkaen befinner seg
    “age”: “string”,      // Alderen til alpakkaen
    “imgSrc”: “string”    // URL til bilde av alpakkaen
  }
]
```
**Merk:** Dette viser kun datatypene og ikke faktisk respons!

#### Dette er endepunktene som skal brukes for å løse oppgaven:
Endepunkt Elementer -- Elementer
https://www.uio.no/studier/emner/matnat/ifi/IN2000/v20/obligatoriske-oppgaver/alpakka0.json     -- 0 
https://www.uio.no/studier/emner/matnat/ifi/IN2000/v20/obligatoriske-oppgaver/alpakka2.json     -- 2 
https://www.uio.no/studier/emner/matnat/ifi/IN2000/v20/obligatoriske-oppgaver/alpakka20.json    -- 20 
https://www.uio.no/studier/emner/matnat/ifi/IN2000/v20/obligatoriske-oppgaver/alpakka200.json   -- 200
https://www.uio.no/studier/emner/matnat/ifi/IN2000/v20/obligatoriske-oppgaver/alpakka2000.json  -- 2000

Du finner også filene her: https://github.uio.no/in2000-v20/Oblig2-Testfiler

#### Hente data
Bruk en valgfritt HTTP-klient (bibliotek) for å hente ut data med en GET-request. Se forelesning 
om API for forslag til HTTP-klienter. Data-hentingen skal gjøres i MainActivity og du skal bruke 
Coroutines for å sikre at kallet gjøres asynkront.

#### Parse JSON til alpakka-objekter
Opprett en ny Kotlin-klasse med navn: Alpaca.kt
Dette skal være en data class som skal representere alpakka-objektene.

**Merk:** Det er viktig at instansvariabler i Alpaca.kt har likt variabelnavn som JSON-objektene.
Når du får respons fra API-et skal dette komme som et JSON-array som inneholder alle
JSON-objektene som skal deserialiseres (parses) til Alpakka-objekter. Til selve
parsingen står du helt fritt til å bruke det biblioteket du ønsker, men Gson er anbefalt.

#### Legge Alpakka-objektene i liste
Til slutt skal du legge alle alpakka-objektene i en MutableList<Alpaca> .
Det er viktig at denne lista har innhold før du fortsetter med neste deloppgave.

**Merk:** Dersom du hentet et tomt JSON-array (ingen JSON-objekter) skal en Toast vises
til bruker.

#### Andre krav:
- Det er viktig at Coroutines benyttes for å sikre asynkrone kall til API-et.
- Appen skal ikke krasje, uansett hvilket av endepunktene det hentes fra.
- Instanser av Alpaca-klassen skal lagre alle 4 variable i hvert JSON-objekt.

#### Tips:
- Husk å gi appen tillatelse til å bruke internett.
- Det anbefales at dere bruker endepunktene som har få elementer når dere skal implementere. Etter dere er ferdig, kan dere teste med endepunktene som har
mange elementer.

#### Utforming:
Du skal ikke lage noe layout til denne deloppgaven.
#### Filernavn:
MainActivity.kt og Alpaca.kt.

## Del 2: RecyclerView
I denne deloppgaven skal du opprette et RecyclerView som skal presentere dataen du
hentet i forrige deloppgave. Før du begynner å løse denne delen bør du lese
dokumentasjonen om relevante biblioteker tilhørende del 2 nevnt innledningsvis.

#### Du skal gjøre endringer i eller opprette disse filene:
- MainActivity.kt
- activity_main.xml
- ListAdapter.kt
- element.xml

#### element.xml
I denne deloppgaven skal alpakkaene i MutableList<Alpaca> representeres ved
hvert sitt CardView. Inne i hvert CardViewet skal det være tre TextViews som viser
informasjonen som navn, alder og posisjonen til alpakkaene, samt et CircleImageView
som skal vise tilhørende bilde av alpakkaen. element.xml (CardView) 

#### ListAdapter.kt
Opprett en Kotlin-klasse med navn ListAdapter.kt. Adapteren passer på at data i
RecyclerViewet blir representert av MutableList<Alpaca> .
ListAdapter skal implementere grensesnittet:
RecyclerView.Adapter<ListAdapter.ViewHolder>
og implementere de tre tilhørende metodene:
- onCreateViewHolder( … )
- onBindViewHolder ( … )
- getItemCount()

**Viktig:** Biblioteket Glide skal brukes for å laste inn bilde spesifisert i “imgSrc”. Dette
kan gjøres programmatisk i adapteren der widgetene blir tilordnet data fra objektene.

#### MainActivity.kt
I denne filen skal du initialisere RecyclerView med en ListAdapter.
#### Utforming
activity_main.xml sin ConstraintLayout skal inneholde en RecyclerView-widget.
#### Filnavn:
MainActivity.kt, activity_main.xml, ListAdapter.kt, element.xml

## Del 3: ProgressBar
For å gjøre appen mer brukervennlig skal en ProgressBar-widget vises når det ventes på
respons fra API-et og samtidig som JSON-objektene parses. For å teste om ProgressBar
fungerer kan du legge inn et delay( … ) i Coroutinen du bruker for å hente data /
parse objekter.
#### Flyt
Når bruker åpner appen skal ProgressBar umiddelbart vises frem til API-kallet er ferdig
og RecyclerViewet er fylt med innhold.
#### Utforming
Du trenger kun å legge til ProgressBar-widgeten i activity_main.xml. Du kan enkelt
midtstille denne om du plasserer den i en RelativeLayout.
#### Filnavn:
MainActivity.kt og activity_main.xml
