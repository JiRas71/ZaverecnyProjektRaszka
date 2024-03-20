package cz.itnetwork.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

/**
 * Třída EntityNotFoundExceptionAdvice poskytuje globální zacházení s výjimkami
 * pro výjimky typu NotFoundException a EntityNotFoundException.
 * Toto je část rámce Spring, která umožňuje definovat chování pro výjimky
 * napříč celou aplikací, místo abychom je museli zachytávat a zpracovávat
 * v každé jednotlivé controller metodě.
 */
@ControllerAdvice
public class EntityNotFoundExceptionAdvice {

    /**
     * Metoda handleEntityNotFoundException je určena k zachycení a zpracování
     * výjimek typu NotFoundException a EntityNotFoundException.
     * Když některá z těchto výjimek nastane v aplikaci, Spring automaticky zavolá tuto metodu.
     * V tomto případě metoda nevrací žádnou odpověď (tělo odpovědi je prázdné),
     * ale nastavuje HTTP status kódu odpovědi na 404 (Not Found),
     * což indikuje, že požadovaný zdroj nebyl nalezen.
     */
    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException() {
    }
}
