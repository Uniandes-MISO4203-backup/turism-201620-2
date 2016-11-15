/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.turism.tests;

import co.edu.uniandes.csw.turism.dtos.detail.BuyDetailDTO;
import co.edu.uniandes.csw.turism.dtos.detail.DestinationDetailDTO;
import co.edu.uniandes.csw.turism.dtos.detail.QuestionDetailDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.QuestionDTO;
import co.edu.uniandes.csw.turism.entities.BuyEntity;
import co.edu.uniandes.csw.turism.entities.DestinationEntity;
import co.edu.uniandes.csw.turism.entities.QuestionEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @generate
 */
public class Utils {
    public static final String apiPath = "api";
    public static final String username = System.getenv("USERNAME_USER");
    public static final String password = System.getenv("PASSWORD_USER");

    /**
     * Returns a {@link QuestionDTO} instance
     *
     * @return
     */
    public static QuestionDTO aQuestionDTO() {
        return new QuestionDTO();
    }

    /**
     * Returns a {@link QuestionEntity} instance
     *
     * @return
     */
    public static QuestionEntity aQuestionEntity() {
        return new QuestionEntity();
    }

    /**
     * Returns a {@link QuestionDetailDTO} instance
     *
     * @return
     */
    public static QuestionDetailDTO aQuestionDetailDTO() {
        return new QuestionDetailDTO();
    }

    /**
     * Returns a list of {@link TripEntity}
     *
     * @return
     */
    public static List<TripEntity> aTripEntityList() {
        return new ArrayList<>();
    }

    /**
     * Returns a Long
     *
     * @return
     */
    public static Long aLong() {
        return Long.MIN_VALUE;
    }

    /**
     * Returns a {@link TripEntity} instance
     * @return
     */
    public static TripEntity aTripEntity() {
        return new TripEntity();
    }

    /**
     * returns a list of {@link DestinationEntity}
     * @return
     */
    public static List<DestinationEntity> aDestinationEntityList() {
        return new ArrayList<>();
    }

    /**
     * Returns an instance of {@link DestinationDetailDTO}
     * @return
     */
    public static DestinationDetailDTO aDestinationDetailDTO() {
        DestinationDetailDTO dto = new DestinationDetailDTO();
        dto.setId(Long.valueOf(0));
        dto.setName("name");
        return dto;
    }

    /**
     * Returns an instance of {@link DestinationEntity}
     * @return
     */
    public static DestinationEntity aDestinationEntity() {
        return new DestinationEntity();
    }

    /**
     * returns a list of {@link BuyEntity}
     * @return
     */
    public static List<BuyEntity> aBuysEntityList() {
        return new ArrayList<>();
    }

    /**
     * Returns an instance of {@link BuyEntity}
     * @return
     */
    public static BuyEntity aButEntity() {
        return new BuyEntity();
    }

    /**
     * returns a list of {@link BuyDetailDTO}
     * @return
     */
    public static List<BuyDetailDTO> aBuyDetailDTOList() {
        return new ArrayList<>();
    }

    /**
     * returns a list of {@link BuyEntity}
     * @return
     */
    public static List<BuyEntity> aButEntityList() {
        return new ArrayList<>();
    }
}
