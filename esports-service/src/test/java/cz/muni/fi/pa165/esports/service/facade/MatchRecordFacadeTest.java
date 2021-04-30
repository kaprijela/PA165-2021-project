package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;
import cz.muni.fi.pa165.esports.entity.MatchRecord;

import cz.muni.fi.pa165.esports.service.BeanMappingService;
import cz.muni.fi.pa165.esports.service.MatchRecordService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.*;

/**
 * @author Elena Álvarez
 */

@RunWith(MockitoJUnitRunner.class)
public class MatchRecordFacadeTest {
    @Mock
    MatchRecordService matchRecordService;

    @Mock
    BeanMappingService beanMappingService;

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
        when(matchRecordService.create(matchRecord)).thenReturn(matchRecord);
        matchRecordFacade.create(matchDTO);

        verify(beanMappingService, times(1)).mapTo(matchDTO, MatchRecord.class);
        verify(matchRecordService, times(1)).create(matchRecord);

        when(matchRecordService.findById(matchDTO.getId())).thenReturn(matchRecord);
        matchRecordFacade.delete(matchDTO.getId());
        verify(matchRecordService, times(1)).delete(matchRecord);
    }

}