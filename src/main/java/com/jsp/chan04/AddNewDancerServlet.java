package com.jsp.chan04;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerMemoryRepo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 역할: 새로운 댄서 정보를 데이터베이스에 등록하기 위해
//      댄서 정보들을 가져와서 처리하는 역할
@WebServlet("/chap04/new-dancer")
public class AddNewDancerServlet extends HttpServlet {

    private DancerMemoryRepo repo = DancerMemoryRepo.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 요청 파라미터를 읽어서 댄서 정보 가져오기
        req.setCharacterEncoding("utf-8");

        String name = req.getParameter("name");
        String crewName = req.getParameter("crewName");
        String danceLevel = req.getParameter("danceLevel");
        String[] genres = req.getParameterValues("genres");

        // 댄서 객체 생성
        Dancer dancer = new Dancer();
        dancer.setName(name);
        dancer.setCrewName(crewName);
        dancer.setDanceLevel(Dancer.DanceLevel.valueOf(danceLevel));

        List<Dancer.Genre> genreList = new ArrayList<>();
        for (String genre : genres) {
            genreList.add(Dancer.Genre.valueOf(genre));
        }
        dancer.setGenres(genreList);

        System.out.println("dancer = " + dancer);

        // 생성된 댄서 객체를 데이터베이스에 저장
        // 데이터베이스 처리에 특화된 객체에게 위임
        repo.save(dancer);

        // 적당한 HTML 응답
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/chap04/result.jsp");
        rd.forward(req, resp);

    }
}
