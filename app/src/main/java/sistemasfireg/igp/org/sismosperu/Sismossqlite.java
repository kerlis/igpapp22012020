package sistemasfireg.igp.org.sismosperu;

public class Sismossqlite {

    private String id;
    private String categoria;
    private String epicentro;
    private String fechautc;
    private String horautc;
    private String intenso;
    private String lat;
    private String lon;
    private String magnitud;
    private String profundidad;
    private String referencia;
    private String simulacro;
    private String tiporeporte;

    public static abstract class NewSismossqlite{

       // public static final String USER_NAME = "user_name";
        //public static final String USER_mob = "user_name";
        //public static final String USER_email = "user_name";




        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CATEGORIA = "categoria";
        public static final String COLUMN_EPICENTRO = "epicentro";
        public static final String COLUMN_FECHAUTC = "fechautc";
        public static final String COLUMN_HORAURC = "horautc";
        public static final String COLUMN_INTENSO = "intenso";
        public static final String COLUMN_LAT = "lat";
        public static final String COLUMN_LON = "lon";
        public static final String COLUMN_MAGNITUD = "magnitud";
        public static final String COLUMN_PROFUNDIDAD = "profundidad";
        public static final String COLUMN_REFERENCIA = "referencia";
        public static final String COLUMN_SIMULACRO = "simulacro";
        public static final String COLUMN_TIPOREPORTE = "tiporeporte";

        public static final String table_name = "Sismos_table";



    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEpicentro() {
        return epicentro;
    }

    public void setEpicentro(String epicentro) {
        this.epicentro = epicentro;
    }

    public String getFechautc() {
        return fechautc;
    }

    public void setFechautc(String fechautc) {
        this.fechautc = fechautc;
    }

    public String getHorautc() {
        return horautc;
    }

    public void setHorautc(String horautc) {
        this.horautc = horautc;
    }

    public String getIntenso() {
        return intenso;
    }

    public void setIntenso(String intenso) {
        this.intenso = intenso;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    public String getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(String profundidad) {
        this.profundidad = profundidad;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSimulacro() {
        return simulacro;
    }

    public void setSimulacro(String simulacro) {
        this.simulacro = simulacro;
    }

    public String getTiporeporte() {
        return tiporeporte;
    }

    public void setTiporeporte(String tiporeporte) {
        this.tiporeporte = tiporeporte;
    }

    public Sismossqlite(String id, String categoria, String epicentro, String fechautc, String horautc, String intenso, String lat, String lon, String magnitud, String profundidad, String referencia, String simulacro, String tiporeporte) {
        this.id = id;
        this.categoria = categoria;
        this.epicentro = epicentro;
        this.fechautc = fechautc;
        this.horautc = horautc;
        this.intenso = intenso;
        this.lat = lat;
        this.lon = lon;
        this.magnitud = magnitud;
        this.profundidad = profundidad;
        this.referencia = referencia;
        this.simulacro = simulacro;
        this.tiporeporte = tiporeporte;
    }

    public Sismossqlite() {
    }

}



