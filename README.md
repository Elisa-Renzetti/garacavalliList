---


---

<h1 id="produzione-bicchieri-–-progetto-thread-java">Produzione Bicchieri – Progetto Thread Java</h1>
<h2 id="descrizione-del-progetto">Descrizione del progetto</h2>
<p>Questo progetto simula un semplice <strong>processo industriale di produzione di bicchieri</strong></p>
<p>utilizzando i <strong>thread in Java</strong>.</p>
<p>Ogni fase della lavorazione è rappresentata da un thread separato, e il programma principale</p>
<p>coordina le varie fasi tramite i metodi <code>sleep()</code> e <code>join()</code>.</p>
<hr>
<h2 id="struttura-del-progetto">Struttura del progetto</h2>
<ul>
<li>
<p><strong>Stampaggio.java</strong> → rappresenta la prima fase (stampaggio del bicchiere).</p>
</li>
<li>
<p><strong>Rifinitura.java</strong> → rappresenta la seconda fase (rifinitura e controllo qualità).</p>
</li>
<li>
<p><strong>ProduzioneBicchieri.java</strong> → classe principale che coordina i thread.</p>
</li>
</ul>
<hr>
<h2 id="funzionamento">Funzionamento</h2>
<ol>
<li>
<p>Il programma avvia la produzione con <code>ProduzioneBicchieri</code>.</p>
</li>
<li>
<p>Viene eseguito il thread di <code>Stampaggio</code>.</p>
</li>
<li>
<p>Dopo la conclusione del primo thread, parte il thread <code>Rifinitura</code>.</p>
</li>
<li>
<p>Ogni thread usa <code>Thread.sleep()</code> per simulare tempi di lavorazione.</p>
</li>
<li>
<p>Il main utilizza <code>join()</code> per sincronizzare le fasi.</p>
</li>
</ol>
<hr>
<h2 id="tecnologie-utilizzate">Tecnologie utilizzate</h2>
<ul>
<li>
<p>Linguaggio: <strong>Java</strong></p>
</li>
<li>
<p>IDE: <strong>IntelliJ IDEA</strong></p>
</li>
<li>
<p>Strumenti aggiuntivi:</p>
</li>
<li>
<p><strong>Visual Paradigm Online</strong> → per creare il diagramma UML</p>
</li>
<li>
<p><strong>JavaDoc</strong> → per generare la documentazione automatica</p>
</li>
</ul>
<hr>
<h2 id="contact">Contact</h2>
<p>Created by Renzetti Lorenzetti Elisa</p>
<p>contact <a href="mailto:renzettielisa3@gmail.com">renzettielisa3@gmail.com</a></p>

