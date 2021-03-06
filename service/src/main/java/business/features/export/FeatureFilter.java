package business.features.export;

import entity.AbstractSegment;
import entity.Feature;
import entity.FeatureType;
import entity.SegmentRepository;

import java.util.ArrayList;
import java.util.List;

public class FeatureFilter {

	private List<FeatureType> notWanted;
	
	public FeatureFilter(List<FeatureType> notWantedFeatures) {
		this.notWanted = notWantedFeatures;
	}
	public SegmentRepository filter(SegmentRepository segmentRepository){
		List<AbstractSegment> segments = segmentRepository.getSegments();
		for (AbstractSegment segment : segments) {
			Feature[] features = segment.getFeatures();
			segment.setFeatures(filterFeatures(features));
		}
		segmentRepository.setSegments(segments);
		return segmentRepository;
	}
	
	private Feature[] filterFeatures(Feature[] features){
		List<Feature> filteredFeatures = new ArrayList<>();
		for (Feature feature : features) {
			if(!notWanted.contains(feature.getFeature())){
				filteredFeatures.add(feature);
			}
		}
		return filteredFeatures.toArray(new Feature[0]);
	}
}
