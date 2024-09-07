public class App {
    public static void main(String[] args) throws Exception {
        Sistema sistema = Sistema.getInstance();
        sistema.init();
        sistema.menuPrincipal();
    }
}
