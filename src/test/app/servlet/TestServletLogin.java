package app.servlet;

import ch.heigvd.amt.models.Official;
import ch.heigvd.amt.models.Team;
import ch.heigvd.amt.services.dao.OfficialManagerLocal;
import ch.heigvd.amt.servlet.LoginServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestServletLogin {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession httpSession;

    @Mock
    HttpServletResponse response;

    @Mock
    OfficialManagerLocal usersDAO;

    LoginServlet servlet;

    @BeforeEach
    public void setup() {
        servlet = new LoginServlet();
        servlet.officialManager = usersDAO;
    }

    @Test
    void doPostSouldCallConnectfunction() throws ServletException, IOException {
        useGoodUsername();
        useGoodPassword();
        actionIsLogin();
        daoReturnUser();
        useSession();

        servlet.doPost(request, response);
        verify(usersDAO, atLeastOnce()).connect("nathan@fluckiger.ch", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");
    }

    @Test
    void doPostSouldAcceptLogin() throws ServletException, IOException {
        useGoodUsername();
        useGoodPassword();
        actionIsLogin();
        daoReturnUser();
        useSession();

        servlet.doPost(request, response);
        verify(usersDAO, atLeastOnce()).connect("nathan@fluckiger.ch", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");
        verify(response, atLeastOnce()).sendRedirect(any());
    }

    Official nathan = new Official("Nathan", "Fluckiger", "nathan@fluckiger.ch", "1234",1, new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix"));

    void useGoodPassword() { when(request.getParameter("password")).thenReturn("1234"); }
    void useGoodUsername() { when(request.getParameter("email")).thenReturn("nathan@fluckiger.ch"); }
    void actionIsLogin() {when(request.getParameter("action")).thenReturn("login");}
    void daoReturnUser() {when(usersDAO.connect("nathan@fluckiger.ch", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4")).thenReturn(nathan);}
    void useSession() {
        when(request.getSession()).thenReturn(httpSession);
    }

    }