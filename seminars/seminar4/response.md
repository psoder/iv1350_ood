# Respons på respons

Vill bara börja med att klargöra att det inte är en personlig attack på någon
och att jag är mer intresserad av att ha en intelektuell diskussion än något
annat.

## I

### a)

Mjukvarudesign är språkoberoende och språket borde inte spela någon roll.
Det viktiga är arkitekturen och programflödet, inte vilken syntax som används.
Ja, vissa saker i syntaxen kan vara ovana, men de är så små att det inte spelar
någon större roll.

### b)

Upplägget på seminarierna, som jag har förstått det, är att man sitter i
grupper om tre och diskuterar en eller flera personers program och ger
feedback på hur det förhåller sig till bedömningskriterierna. Då seminariet
är 120 minuter, med 30 minuter presentation, 15 minuter paus, och 5-10 minuter
till att komma igång, ger det ca. 70 minuter för att granska tre program,
eller ~25 minuter per program. Under den här tiden hinner man (åtminstone inte
jag) inte sätta sig in i koden, granska den och ge kvalitativ feedback. Det
leder till att de som granskar istället för att titta på vad som spelar roll
(programflöde, struktur, etc) letar efter ytliga fel (t.ex. dåliga variabelnamn
eller kommentarer). Det ytliga kan absolut vara viktigt att ha i åtanke, men
det är kanske inte det granskningen borde fokusera på. Om den granskande
gruppen istället väljer att lägga hela tiden på att ge feedback på en persons
arbete blir det bättre för alla inblandade, förutom de som inte får någon
feedback alls.

Jag tror att ett mer givande upplägg hade varit om man i par granskar en annan
students arbete. Fördelarna med detta upplägg är att 1) Alla får feedback på
vad det har gjort. 2) Ger tid att sätta sig in ordentligt i någons arbete och
ge konstruktiv feedback. 3) Diskussionsasspekten är fortfarande bevarad då den
granskande studenten kan ställa direkta frågor om koden. Den enda nackdelen jag
ser med detta upplägg är att det kan vara svårare att ge "hård" feedback till
någon som man sitter brevid. Jag ser dock inte det som ett rimligt motargument
då det är viktigt att både kunna ge och ta feedback utan att låta känslorna
komma ivägen.

### c)

Jag inser att det här kommer att låta väldigt arrogant och nedlåtande mot
andra studnter, men det är inte för att vara elak utan för att jag tror det är
sant som jag skriver det. Jag tror endast att det handlar om att jag har betydligt
mycket mer erfarenhet än vad de andra (och att upplägget på seminarierna inte
tillåter en att resoneraom programen) har och inte att jag är något naturligt geni.

Baserat på vad jag har sett folk skriva och ge som feedback tror jag tyvärr
inte att att det hade kunnat ge någon konstruktiv feedback om de inte har koll
på Exceptions eller kan lista ut hur ett visst mönster fungerar. Av den anledningen
tror jag inte heller att det finns någon som helst mening med att skriva om koden
i Java då deras feedback förmodligen inte spelar någon roll. Jag inser att bara
för att man kan programera så betyder det inte att ens design är bra, men de
brukar ofta vara relaterade (och baserat på den feedback jag fått av dig verkar
jag vara något på spåren).

## II

Att View annropar sale i saleList tror inte jag riktigt stämmer.
controller.register.sale i Kotlin motsvarar controller.getRegister().getSale()
i Java, d.v.s. att kunna få tillgång till Sale är en del av Controllerns publika
API. Ja, en elegantare lösning hade varit att skicka tillbaks det nya
tillståndet i returvärdet för de funktioner som annropas, men vad är skillnaden
på att göra ett metodanrop som användaren eller programet initierar? Frågan om
mutability och DTO:er är en annan, men jag medger att det hade varit bättre att
låta getRegister returnera en icke-mutable variabel.

## III

Det är inte idiomatisk Kotlin att skriva ut domänen och projektet i
paketnamnet då det inte tillför något värde. Men om det är ett krav så är det.

## IV

Som nämnt tidigare så är Java och Kotlin lite olika i hur variabler och
getter/setters fungerar, se nedan.

```kotlin
// Kotlin
var a = 10
    private set
val b = x
val c = 3
```

```java
// Java
private int a = 10;
public final int b = x;
public final static int c = 3;

public int getA() { return a; }
private void setA(int a) { this.a = a; }
```

Med det i åtanke ser jag ingenting som sticker sticker ut som dåligt inkapslat.

## V

Jag håller med om att diskussionen fokuserar lite för mycket på fel saker
och inte så mycket på det faktiska programmet. Jag skulle dock inte gå så långt
som att säga att diskussionen handlar om objektorienterad kontra funktionell
programering, då de procedurella och objektorienterade paradigmen båda är
imperativa.

Jag förstår att det här inte riktigt är rätt forum att ta debatera procedurell
kontra objektorienterad programering, men jag står fast vid att om programmet
hade varit procedurellt skrivet hade koden varit lättare att förstå för alla.
Problemet med objektorienterad programering är att bara för att någonting är
inkapslat in och bortabstraherat så blir det inte lättare att förstå koden.
Jag argumenterar inte emot att man helt borde slopa abstraktion eller inkapsling,
men att ha det i såpass hög grad som lärs ut i kursen gör allting svårare än det
behöver vara. Att dela in ett projekt i flera lager är en bra idé, men att dela
in varje lager i 20 olika klasser löser inga problem utan gör endast allt svårare
att förstå. Om jag hade haft mer tid (och ett program som kräver mer än tre
operationer) hade det varit intressant att skriva programmet i både
objektorienterad och procedurell stil och sedan jämföra dem.

Som en demo skrev jag om modell-lagret (det intressanta är rad 36-86), se `Model.kt`.
Rad 111-260 är i princip oförändrade från inlämningen. Anledningen till att allt
ligger i samma fil är för att det är lättare att dela så. Till en början ser det
mer komplicerat ut då det är större metoder, men koden som helhet är lättare att
förstå eftersom att man endast behöver förstå vad en metod gör istället och hur
den fungerar. Antingen så var det en dålig inkapsling till att börja med (i vilket
fall det är lite oroväckande att jag inte fått någon komentar på det) eller så
behövdes den inte. Det är lite svårt att demonstrera min poäng med ett så begränsat
exempel, men jag hoppas att du i framtiden kan tänka på att inte över abstrahera/kapsla
in funktionalitet.

## Övrigt

Något annat som borde tas upp är att folk måste skriva någon form av README
på hur man kör programmet, att få en massa källkod utan någon som helst
dokumentation gör allting mycket svårare än det borde vara.

En annan grej som borde tas upp är hur programen distruberas mellan de olika
grupperna. Som det ser ut nu skickar en person i en grupp sitt program till
endast en person i den andra gruppen, vilket gör allting mycket jobbigare när
man ska granska programmet då man behöver dela det inom granskningsgruppen.
Varför inte göra så att alla skickar till alla i gruppen som ska granska?

Det blev också något längre än vad jag tänkte mig men jag uppskattar
att du tog dig tiden att läsa det.
