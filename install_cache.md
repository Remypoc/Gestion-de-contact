# Installer ehcache manuellement (intellij)

1. Télécharger ehcache 2.10.4 [ici](http://www.ehcache.org/downloads/)
2. Télécharger hibernate-ehcache [ici](https://repo1.maven.org/maven2/org/hibernate/hibernate-ehcache/5.1.1.Final/) 
(hibernate-ehcache-5.1.1.Final.jar)
3. Placer les .jar (pour ehcache, ceux dans ehcache-2.10.4/lib) dans les dossiers src/lib et WEB-INF/lib
4. Ouvrir Project Structure (File &rarr; Project Structure &rarr; Libraries)
5. Ajouter ehcache-2.10.4.jar puis hibernate-ehcache-5.1.1.Final.jar dans le classpath