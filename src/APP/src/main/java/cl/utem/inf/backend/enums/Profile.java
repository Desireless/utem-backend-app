package cl.utem.inf.backend.enums;

/**
 * Esta enumeración permite identificar el Perfil que tiene un usuario dentro de
 * la aplicación.
 *
 * @author jpbb
 */
public enum Profile {
    /**
     * 0 - Perfil de estudiante
     */
    STUDENT {
        @Override
        public String getName() {
            return "EStudiante";
        }
    }, 
    /**
     * 1 - Perfile académico
     */
    ACADEMIC {
        @Override
        public String getName() {
            return "Académico";
        }
    }, 
    /**
     * 2 - Funcionario
     */
    STAFF {
        @Override
        public String getName() {
            return "Funcionario";
        }
    };
    
    public abstract String getName();
}
