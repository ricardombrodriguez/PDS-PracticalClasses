public class Movie { // Produto

    private final String title;
    private final int year;
    private final Person director;
    private final Person writer;
    private final String series;
    private final List<Person> cast;
    private final List<Place> locations;
    private final List<String> languages;
    private final List<String> genres;
    private final boolean isTelevision;
    private final boolean isNetflix;
    private final boolean isIndependent;

    public static class MovieBuilder { // Builder  
        private final String title;
        private final int year;

        // optional arguments
        private Person director;
        private Person writer;        
        private List<String> genres;
        private List<String> languages = null;
        private String series = null;
        private List<Person> cast = null;
        private List<Place> locations = null;
        private boolean isTelevision = false;
        private boolean isNetflix = false;
        private boolean isIndependent = false;

        public MovieBuilder(String title, int year) {
            this.title = title;
            this.year = year;
        }

        public MovieBuilder director(Person dir) {
            director = dir;
            return this;
        }

        public MovieBuilder writer(Person wr) {
            writer = wr;
            return this;
        }

        public MovieBuilder genres(List<String> gen) {
            genres = gen;
            return this;
        }

        public MovieBuilder languages(List<String> lan) {
            languages = lan;
            return this;
        }

        public MovieBuilder series(String ser) {
            series = ser;
            return this;
        }

        public MovieBuilder cast(List<Person> cas) {
            cast = cas;
            return this;
        }

        public MovieBuilder locations(List<String> local) {
            locations = local;
            return this;
        }

        public MovieBuilder isTelevision(boolean isTV) {
            isTelevision = isTV;
            return this;
        }

        public MovieBuilder isNetflix(boolean isNet) {
            isNetflix = isNet;
            return this;
        }

        public MovieBuilder isIndependent(boolean isInd) {
            isIndependent = isInd;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    private Movie(MovieBuilder builder) {
        title = MovieBuilder.title;
        year = MovieBuilder.year;
        director = MovieBuilder.director;
        writer = MovieBuilder.writer;
        series = MovieBuilder.series;
        cast = MovieBuilder.cast;
        locations = MovieBuilder.locations;
        languages = MovieBuilder.languages;
        genres = MovieBuilder.genres;
        isTelevision = MovieBuilder.isTelevision;
        isNetflix = MovieBuilder.isNetflix;
        isIndependent = MovieBuilder.isIndependent;
    }
}