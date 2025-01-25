package br.com.ifs.noticiasg1project.model.role;

public enum RoleUsuario {
    ADMIN("admin"),
    USER("user");

    private String role;

    RoleUsuario(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
