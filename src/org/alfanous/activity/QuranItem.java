package org.alfanous.activity;

public class QuranItem {

	private int globalId;
	private int ayaId;
	private String ayaText;
	private String ayaTextUthmani;
	private String ayaRecitation;
	
	private String subject;
	private String chapter;
	private String topic;
	private String subtopic;
	private int suraId;
	private String suraName;
	private String suraType;
	private int sura_order;
	private int juz;
	private int positionHizb;
	private int nisf;
	private int positionRubu;
	private int positionPage;
	private int positionManzil;
	private int ruku;
	private boolean sajdaExists;
	private int sajda_id;
	private String sajda_type;
	
	private int suraStatAyas;
	private int suraStatLetters;
	private int suraStatGODNames;
	private int suraStatWords;
	
	private int statLetters;
	private int statGodNames;
	private int statWords;
	
	private String themeChapter;
	private String themeTopic;
	private String themeSubTopic;
	
	public int getSuraStatAyas() {
		return suraStatAyas;
	}

	public void setSuraStatAyas(int suraStatAyas) {
		this.suraStatAyas = suraStatAyas;
	}

	public int getSuraStatLetters() {
		return suraStatLetters;
	}

	public void setSuraStatLetters(int suraStatLetters) {
		this.suraStatLetters = suraStatLetters;
	}

	public int getSuraStatGODNames() {
		return suraStatGODNames;
	}

	public void setSuraStatGODNames(int suraStatGODNames) {
		this.suraStatGODNames = suraStatGODNames;
	}

	public int getSuraStatWords() {
		return suraStatWords;
	}

	public void setSuraStatWords(int suraStatWords) {
		this.suraStatWords = suraStatWords;
	}

	public int getGlobalId() {
		return globalId;
	}

	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}

	public int getAyaId() {
		return ayaId;
	}

	public void setAyaId(int ayaId) {
		this.ayaId = ayaId;
	}

	public String getAyaText() {
		return ayaText;
	}

	public void setAyaText(String ayaText) {
		this.ayaText = ayaText;
	}

	public String getAyaTextUthmani() {
		return ayaTextUthmani;
	}

	public void setAyaTextUthmani(String ayaTextUthmani) {
		this.ayaTextUthmani = ayaTextUthmani;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getSubtopic() {
		return subtopic;
	}

	public void setSubtopic(String subtopic) {
		this.subtopic = subtopic;
	}

	public int getSuraId() {
		return suraId;
	}

	public void setSuraId(int suraId) {
		this.suraId = suraId;
	}

	public String getSuraName() {
		return suraName;
	}

	public void setSuraName(String sura) {
		this.suraName = sura;
	}

	public String getSuraType() {
		return suraType;
	}

	public void setSuraType(String suraType) {
		this.suraType = suraType;
	}

	public int getSura_order() {
		return sura_order;
	}

	public void setSura_order(int sura_order) {
		this.sura_order = sura_order;
	}

	public int getJuz() {
		return juz;
	}

	public void setJuz(int juz) {
		this.juz = juz;
	}

	public int getPositionHizb() {
		return positionHizb;
	}

	public void setPositionHizb(int positionHizb) {
		this.positionHizb = positionHizb;
	}

	public int getNisf() {
		return nisf;
	}

	public void setNisf(int nisf) {
		this.nisf = nisf;
	}

	public int getPositionRubu() {
		return positionRubu;
	}

	public void setPositionRubu(int positionRubu) {
		this.positionRubu = positionRubu;
	}

	public int getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(int positionPage) {
		this.positionPage = positionPage;
	}

	public int getPositionManzil() {
		return positionManzil;
	}

	public void setPositionManzil(int positionManzil) {
		this.positionManzil = positionManzil;
	}

	public int getRuku() {
		return ruku;
	}

	public void setRuku(int ruku) {
		this.ruku = ruku;
	}

	public boolean isSajdaExists() {
		return sajdaExists;
	}

	public void setSajdaExists(boolean sajda) {
		this.sajdaExists = sajda;
	}

	public int getSajda_id() {
		return sajda_id;
	}

	public void setSajda_id(int sajda_id) {
		this.sajda_id = sajda_id;
	}

	public String getSajda_type() {
		return sajda_type;
	}

	public void setSajda_type(String sajda_type) {
		this.sajda_type = sajda_type;
	}
	public String getAyaRecitation() {
		return ayaRecitation;
	}

	public void setAyaRecitation(String ayaRecitation) {
		this.ayaRecitation = ayaRecitation;
	}

	public int getStatLetters() {
		return statLetters;
	}

	public void setStatLetters(int statLetters) {
		this.statLetters = statLetters;
	}

	public int getStatGodNames() {
		return statGodNames;
	}

	public void setStatGodNames(int statGodNames) {
		this.statGodNames = statGodNames;
	}

	public int getStatWords() {
		return statWords;
	}

	public void setStatWords(int statWords) {
		this.statWords = statWords;
	}

	public String getThemeChapter() {
		return themeChapter;
	}

	public void setThemeChapter(String themeChapter) {
		this.themeChapter = themeChapter;
	}

	public String getThemeTopic() {
		return themeTopic;
	}

	public void setThemeTopic(String themeTopic) {
		this.themeTopic = themeTopic;
	}

	public String getThemeSubTopic() {
		return themeSubTopic;
	}

	public void setThemeSubTopic(String themeSubTopic) {
		this.themeSubTopic = themeSubTopic;
	}



}
