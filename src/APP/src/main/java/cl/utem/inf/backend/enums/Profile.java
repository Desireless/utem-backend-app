package cl.utem.inf.backend.enums;

/**
 * Esta enumeración permite identificar el Perfil que tiene un usuario dentro de
 * la aplicación.
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
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

    /**
     * Obtiene el nombre del perfil.
     *
     * @return Nombre del perfil.
     */
    public abstract String getName();
}
