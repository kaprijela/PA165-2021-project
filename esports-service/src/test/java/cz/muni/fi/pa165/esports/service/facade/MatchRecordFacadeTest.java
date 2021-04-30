package cz.muni.fi.pa165.esports.service.facade;

import static org.testng.Assert.*;
import cz.muni.fi.pa165.esports.dao.TeamDao;
import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Team;

import cz.muni.fi.pa165.esports.service.BeenMappingService;
import cz.muni.fi.pa165.esports.service.MatchRecordService;
import cz.muni.fi.pa165.esports.service.TeamService;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.mockito.InjectMocks;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * @author Elena Álvarez
 */

@RunWith(MockitoJUnitRunner.class)
public class MatchRecordFacadeTest {
    @Mock
    MatchRecordService matchRecordService;

    @Mock
    BeenMappingService beanMappingService;

    @InjectMocks
    MatchRecordFacadeImpl matchRecordFacade;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllMatchRecords() {
        matchRecordFacade.getAllMatchRecord();
        verify(matchRecordService, times(1)).findAll();
    }



    @Test
    public void  testCreateMatchRecord(){
        MatchRecordDTO matchRecordDTO = new MatchRecordDTO();
        matchRecordDTO.setId(1L);
        matchRecordDTO.setScore(0);

        MatchRecord matchRecord = new MatchRecord();
        matchRecord.setId(1L);
        matchRecord.setScore(0);

        when(beanMappingService.mapTo(matchRecordDTO, MatchRecord.class)).thenReturn(matchRecord);
        when(matchRecordService.create(matchRecord)).thenReturn(matchRecord);
        matchRecordFacade.create(matchRecordDTO);
        verify(beanMappingService, times(1)).mapTo(matchRecordDTO, MatchRecord.class);
        verify(matchRecordService, times(1)).create(matchRecord);

    }

    @Test
    public void testDeleteMatchRecord(){
        MatchRecordDTO matchDTO = new MatchRecordDTO();
        matchDTO.setId(1L);
        matchDTO.setScore(0);

        MatchRecord matchRecord = new MatchRecord();
        matchRecord.setId(1L);
        matchRecord.setScore(0);

        when(beanMappingService.mapTo(matchDTO, MatchRecord.class)).thenReturn(matchRecord);
        matchRecordFacade.create(matchDTO);

        verify(beanMappingService, times(1)).mapTo(matchDTO, MatchRecord.class);
        verify(matchRecordService, times(1)).create(matchRecord);

        matchRecordFacade.delete(matchDTO.getId());
        verify(matchRecordService, times(1)).delete(matchRecord);
    }

}