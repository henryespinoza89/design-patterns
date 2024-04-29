package com.hespinoza.designpatterns.fintech.application;

import com.hespinoza.designpatterns.fintech.domain.Usuario;
import com.hespinoza.designpatterns.fintech.domain.impl.SingletonConfig;
import com.hespinoza.designpatterns.fintech.domain.impl.SingletonCounter;
import com.hespinoza.designpatterns.fintech.domain.impl.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class RestUsuarioController {
  // Considera que en el contexto de Spring Framework
  // 1.Singleton por defecto: cuando declaras un componente
  // (como un servicio, un controlador, un repositorio, etc.) en Spring utilizando
  // anotaciones como @Component, @Service, @RestController, etc., Spring crea una
  // única instancia de ese componente y la mantiene en su contexto de aplicación
  // durante toda la vida del contexto de la aplicación.
  // 2.Inyección de dependencias: Spring se encarga de inyectar las dependencias a
  // través de la instancia singleton del componente. Si un componente A tiene una
  // dependencia de otro componente B, Spring inyectará la misma instancia de B en
  // todas las instancias de A que se creen.
  // 3.Scope de Singleton: Este es el comportamiento por defecto para los componentes
  // de Spring. Puedes cambiar el scope de un componente utilizando la anotación
  // @Scope, pero si no se especifica, será singleton.
  private final UsuarioService usuarioService;
  // Agregamos la instancia de la clase para realizar algunas operaciones de
  // incremento y decremento en el contador.
  private final SingletonCounter singletonCounter;
  // Agregamos la instancia de la clase SingletonConfig
  private final SingletonConfig singletonConfig;

  public RestUsuarioController(UsuarioService usuarioService, SingletonCounter singletonCounter, SingletonConfig singletonConfig) {
    this.usuarioService = usuarioService;
    this.singletonCounter = singletonCounter;
    this.singletonConfig = singletonConfig;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
    Usuario usuario = usuarioService.obtenerUsuario(id);
    singletonCounter.increment(); // Incremento del contador
    return ResponseEntity.ok(usuario);
  }

  @PostMapping
  public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
    Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
    singletonCounter.increment(); // Incremento del contador
    return ResponseEntity.ok(nuevoUsuario);
  }

  @GetMapping("/contador")
  public ResponseEntity<Integer> obtenerContador() {
    int contador = singletonCounter.getCount();
    return ResponseEntity.ok(contador);
  }

  @GetMapping("/config")
  public ResponseEntity<String> obtenerConfiguracion() {
    // Al acceder al endpoint obtendremos la información de la configuración de la
    // aplicación que se gestiona a través de la instancia única de SingletonConfig.
    // Puedes modificar la configuración en tiempo de ejecución y verificar que la
    // instancia única mantiene los cambios en toda la aplicación.
    String appName = singletonConfig.getAppName();
    int maxConnections = singletonConfig.getMaxConnections();
    String configInfo = "Nombre de la aplicación: " + appName + ", Máx. Conexiones: " + maxConnections;
    return ResponseEntity.ok(configInfo);
  }
}
